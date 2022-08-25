package com.bigcake.airquality.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigcake.airquality.domain.Result
import com.bigcake.airquality.domain.entity.AirQuality
import com.bigcake.airquality.domain.usecase.GetAirQualityUseCase
import com.bigcake.airquality.presentation.mapper.toCardData
import com.bigcake.airquality.presentation.mapper.toListItem
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

    fun onRefresh() {
        fetchAirQualities(refresh = true)
    }

    private fun fetchAirQualities(refresh: Boolean = false) {
        getAirQualityUseCase.invoke(refresh)
            .onEach { handleAirQualitiesResult(it) }
            .launchIn(viewModelScope)
    }

    private fun handleAirQualitiesResult(result: Result<List<AirQuality>>) {
        when (result) {
            is Result.Loading -> {
                result.data?.let {
                    val divideResult = AirQualityDivider.divideByPm25(result.data)
                    state = state.copy(
                        allItems = result.data.map { it.toListItem() },
                        pm25Threshold = divideResult.divider,
                        lowPm25Items = divideResult.lowPm25Items.map { it.toCardData() },
                        highPm25Items = divideResult.highPm25Items.map { it.toListItem() },
                        error = "",
                        isLoading = true
                    )
                }
            }
            is Result.Success -> {
                val divideResult = AirQualityDivider.divideByPm25(result.data)
                state = state.copy(
                    allItems = result.data.map { it.toListItem() },
                    pm25Threshold = divideResult.divider,
                    lowPm25Items = divideResult.lowPm25Items.map { it.toCardData() },
                    highPm25Items = divideResult.highPm25Items.map { it.toListItem() },
                    error = "",
                    isLoading = false
                )
            }
            is Result.Failure -> {
                state = HomeViewState(error = result.message)
            }
        }
    }
}