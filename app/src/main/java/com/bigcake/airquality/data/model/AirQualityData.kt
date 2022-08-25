package com.bigcake.airquality.data.model

import androidx.annotation.VisibleForTesting

data class AirQualityData(
    val siteId: String,
    val siteName: String,
    val county: String,
    val pm25: String,
    val aqi: String,
    val status: String
) {
    companion object {
        @VisibleForTesting
        fun ofSiteId(siteId: String) = AirQualityData(siteId, "", "", "", "", "")
    }
}
