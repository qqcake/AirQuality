package com.bigcake.airquality.cache

import com.bigcake.airquality.cache.model.AirQualityObj
import com.bigcake.airquality.data.model.AirQualityData

fun List<AirQualityData>.toCache() = this.map(AirQualityData::toCache)

private fun AirQualityData.toCache() = AirQualityObj(
    aqi = aqi,
    county = county,
    pm25 = pm25,
    siteid = siteId,
    sitename = siteName,
    status = status,
)

fun AirQualityObj.toData() = AirQualityData(
    siteId = siteid,
    siteName = sitename,
    county = county,
    status = status,
    aqi = aqi,
    pm25 = pm25
)