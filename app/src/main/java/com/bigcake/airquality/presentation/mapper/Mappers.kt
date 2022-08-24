package com.bigcake.airquality.presentation.mapper

import com.bigcake.airquality.domain.entity.AirQuality
import com.bigcake.airquality.presentation.model.AirQualityCardData
import com.bigcake.airquality.presentation.model.AirQualityItemData

fun AirQuality.toListItem(): AirQualityItemData {
    val isAirQualityGood = status == "良好"
    return AirQualityItemData(
        siteId = siteId.toString(),
        siteName = siteName,
        county = county,
        status = if (isAirQualityGood) {
            "The status is good, we want to go out to have fun"
        } else {
            status
        },
        pm25 = getPm25DisplayText(pm25),
        onClickText = if (!isAirQualityGood) "AQI is $aqi" else "",
        showArrow = !isAirQualityGood,
    )
}

fun AirQuality.toCardData(): AirQualityCardData {
    return AirQualityCardData(
        siteId = siteId.toString(),
        siteName = siteName,
        county = county,
        status = status,
        pm25 = getPm25DisplayText(pm25)
    )
}

private fun getPm25DisplayText(pm25: Int): String {
    return if (pm25 == AirQuality.INVALID_PM25) "--" else pm25.toString()
}