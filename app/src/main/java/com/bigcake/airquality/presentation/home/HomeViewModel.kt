package com.bigcake.airquality.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigcake.airquality.domain.Result
import com.bigcake.airquality.domain.entity.AirQuality
import com.bigcake.airquality.domain.usecase.GetAirQualityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAirQualityUseCase: GetAirQualityUseCase
) : ViewModel() {
    var state by mutableStateOf(HomeViewState())
        private set

    init {
        fetchAirQualities()
    }

    private fun fetchAirQualities() {
        state = HomeViewState(isLoading = true)
        getAirQualityUseCase.invoke()
            .onEach { handleAirQualitiesResult(it) }
            .launchIn(viewModelScope)
    }

    private fun handleAirQualitiesResult(result: Result<List<AirQuality>>) {
        when (result) {
            is Result.Success -> {
                val newItems = result.data
                val lowPm25Items = mutableListOf<AirQuality>()
                val highPm25Items = mutableListOf<AirQuality>()
                result.data.forEach {
                    if (it.pm25 <= state.pm25Threshold) {
                        lowPm25Items.add(it)
                    } else {
                        highPm25Items.add(it)
                    }
                }
                state = state.copy(
                    allItems = newItems,
                    lowPm25Items = lowPm25Items,
                    highPm25Items = highPm25Items,
                    error = "",
                    isLoading = false
                )
            }
            is Result.Failure -> state = HomeViewState(error = result.message)
        }
    }
}