package com.bigcake.airquality.data.mapper

import com.bigcake.airquality.data.model.AirQualityData
import com.bigcake.airquality.domain.entity.AirQuality

fun List<AirQualityData>.toDomain(): List<AirQuality> {
    return this.map(AirQualityData::toDomain)
}

private fun AirQualityData.toDomain(): AirQuality {
    return AirQuality(
        siteName = siteName,
        siteId = siteId.toInt(),
        county = county,
        status = status,
        pm25 = if (pm25.isNotEmpty()) pm25.toInt() else AirQuality.INVALID_PM25,
        aqi = if (aqi.isNotEmpty()) aqi.toInt() else AirQuality.INVALID_PM25,
    )
}