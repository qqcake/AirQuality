package com.bigcake.airquality.domain.entity

data class AirQuality(
    val siteId: Int,
    val county: String,
    val siteName: String,
    val status: String,
    val pm25: Int,
    val aqi: Int,
) {
    companion object {
        const val INVALID_PM25 = -1
    }
}
