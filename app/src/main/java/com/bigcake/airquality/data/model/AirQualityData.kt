package com.bigcake.airquality.data.model

data class AirQualityData(
    val siteId: String,
    val siteName: String,
    val county: String,
    val pm25: String,
    val aqi: String,
    val status: String
)
