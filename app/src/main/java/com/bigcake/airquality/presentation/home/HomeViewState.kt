package com.bigcake.airquality.presentation.home

import com.bigcake.airquality.presentation.model.AirQualityCardData
import com.bigcake.airquality.presentation.model.AirQualityItemData

data class HomeViewState(
    val allItems: List<AirQualityItemData> = emptyList(),
    val lowPm25Items: List<AirQualityCardData> = emptyList(),
    val highPm25Items: List<AirQualityItemData> = emptyList(),
    val pm25Threshold: Int = 30,
    val isLoading: Boolean = false,
    val error: String = "",
)
