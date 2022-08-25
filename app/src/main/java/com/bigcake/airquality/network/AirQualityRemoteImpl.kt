package com.bigcake.airquality.network

import com.bigcake.airquality.data.model.AirQualityData
import com.bigcake.airquality.network.api.AirQualityService
import com.bigcake.airquality.data.source.AirQualityRemote
import javax.inject.Inject

class AirQualityRemoteImpl @Inject constructor(
    private val airQualityService: AirQualityService
) : AirQualityRemote {
    override suspend fun getAirQualities(): List<AirQualityData> {
        return airQualityService.getAirQualities().records.toData()
    }
}