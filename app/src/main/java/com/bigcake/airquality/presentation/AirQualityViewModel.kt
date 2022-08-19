package com.bigcake.airquality.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bigcake.airquality.domain.entity.AirQuality
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AirQualityViewModel : ViewModel() {
    var state by mutableStateOf(AirQualityState())
        private set

    init {
        fetchAirQualities()
    }

    fun onGenerateButtonClicked() {
        fetchAirQualities()
    }

    private fun fetchAirQualities() {
        val newItems = DummyItemGenerator.append()
        val lowPm25Items = mutableListOf<AirQuality>()
        val highPm25Items = mutableListOf<AirQuality>()
        newItems.forEach {
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
        )
    }
}

private object DummyItemGenerator {
    private val STATUS_LIST = listOf("良好", "普通", "還有啥")
    private val dummyItems = mutableListOf<AirQuality>()
    private var siteId = 1
    fun append(): List<AirQuality> {
        return dummyItems.apply {
            val siteId = siteId++
            add(
                AirQuality(
                    siteId = siteId,
                    county = "County $siteId",
                    siteName = "SiteName $siteId",
                    status = STATUS_LIST.random(),
                    pm25 = (1..100).random(),
                )
            )
        }
    }
}