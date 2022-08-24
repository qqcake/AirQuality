package com.bigcake.airquality.presentation.home

import com.bigcake.airquality.domain.entity.AirQuality

object AirQualityDivider {
    const val DEFAULT_PM25_DIVIDER = 30
    fun divideByPm25(airQualities: List<AirQuality>): DivideByPm25Result {
        if (airQualities.isNullOrEmpty()) {
            return DivideByPm25Result.EMPTY
        }
        val divider = calculateAveragePm25(airQualities)
        val lowPm25Items = mutableListOf<AirQuality>()
        val highPm25Items = mutableListOf<AirQuality>()
        airQualities.forEach {
            if (it.pm25 <= divider || it.pm25 == AirQuality.INVALID_PM25) {
                lowPm25Items.add(it)
            } else {
                highPm25Items.add(it)
            }
        }
        return DivideByPm25Result(
            divider = divider,
            lowPm25Items = lowPm25Items,
            highPm25Items = highPm25Items
        )
    }

    private fun calculateAveragePm25(airQualities: List<AirQuality>): Int {
        val validItems = airQualities.filter { it.pm25 != AirQuality.INVALID_PM25 }
        return validItems.sumOf { it.pm25 } / validItems.size
    }

    data class DivideByPm25Result(
        val divider: Int,
        val lowPm25Items: List<AirQuality>,
        val highPm25Items: List<AirQuality>
    ) {
        companion object {
            val EMPTY = DivideByPm25Result(DEFAULT_PM25_DIVIDER, emptyList(), emptyList())
        }
    }
}