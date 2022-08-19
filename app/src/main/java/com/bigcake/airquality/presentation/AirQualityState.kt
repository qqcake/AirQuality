package com.bigcake.airquality.presentation

import com.bigcake.airquality.domain.entity.AirQuality

data class AirQualityState(
    val allItems: List<AirQuality> = emptyList(),
    val lowPm25Items: List<AirQuality> = emptyList(),
    val highPm25Items: List<AirQuality> = emptyList(),
    val pm25Threshold: Int = 30,
    val filterText: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
)
