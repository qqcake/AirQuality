package com.bigcake.airquality.data.source

import com.bigcake.airquality.data.model.AirQualityData
import kotlin.time.Duration

interface AirQualityLocal {
    suspend fun getAirQualities(): List<AirQualityData>
    suspend fun setAirQualities(airQualities: List<AirQualityData>)
    fun isExpired(maxAge: Duration): Boolean
}