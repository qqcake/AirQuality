package com.bigcake.airquality.presentation.model

data class AirQualityCardData(
    val siteId: String,
    val county: String,
    val siteName: String,
    val status: String,
    val pm25: String,
)
