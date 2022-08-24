package com.bigcake.airquality.presentation.model

data class AirQualityItemData(
    val siteId: String,
    val county: String,
    val siteName: String,
    val status: String,
    val pm25: String,
    val showArrow: Boolean,
    val onClickText: String,
)
