package com.bigcake.airquality.presentation.home

import com.bigcake.airquality.domain.entity.AirQuality

data class HomeViewState(
    val allItems: List<AirQuality> = emptyList(),
    val lowPm25Items: List<AirQuality> = emptyList(),
    val highPm25Items: List<AirQuality> = emptyList(),
    val pm25Threshold: Int = 30,
    val isLoading: Boolean = false,
    val error: String = "",
)
