package com.bigcake.airquality.domain.entity

import androidx.annotation.VisibleForTesting

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

        @VisibleForTesting
        fun ofPm25(pm25: Int): AirQuality {
            return AirQuality(
                pm25 = pm25,
                siteId = 0,
                siteName = "",
                county = "",
                aqi = 0,
                status = ""
            )
        }
    }
}
