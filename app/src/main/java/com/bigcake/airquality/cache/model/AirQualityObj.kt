package com.bigcake.airquality.cache.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirQualityObj(
    @Json(name = "aqi")
    val aqi: String,
    @Json(name = "county")
    val county: String,
    @Json(name = "pm2.5")
    val pm25: String,
    @Json(name = "siteid")
    val siteid: String,
    @Json(name = "sitename")
    val sitename: String,
    @Json(name = "status")
    val status: String,
)
