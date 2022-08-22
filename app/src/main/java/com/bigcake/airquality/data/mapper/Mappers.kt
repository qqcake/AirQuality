package com.bigcake.airquality.data.mapper

import com.bigcake.airquality.data.model.AirQualitiesDto
import com.bigcake.airquality.domain.entity.AirQuality

fun AirQualitiesDto.Record.toDomain(): AirQuality {
    return AirQuality(
        siteName = sitename,
        siteId = siteid.toInt(),
        county = county,
        status = status,
        pm25 = if (pm25.isNotEmpty()) pm25.toInt() else 0
    )
}