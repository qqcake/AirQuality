package com.bigcake.airquality.network

import com.bigcake.airquality.data.model.AirQualityData
import com.bigcake.airquality.network.model.AirQualitiesDto

fun List<AirQualitiesDto.Record>.toData(): List<AirQualityData> {
    return this.map(AirQualitiesDto.Record::toData)
}

private fun AirQualitiesDto.Record.toData(): AirQualityData {
    return AirQualityData(
        siteId = siteid,
        siteName = sitename,
        county = county,
        status = status,
        pm25 = pm25,
        aqi = aqi,
    )
}