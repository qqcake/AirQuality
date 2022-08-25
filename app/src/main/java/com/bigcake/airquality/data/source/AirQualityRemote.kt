package com.bigcake.airquality.data.source

import com.bigcake.airquality.data.model.AirQualityData

interface AirQualityRemote {
    suspend fun getAirQualities(): List<AirQualityData>
}