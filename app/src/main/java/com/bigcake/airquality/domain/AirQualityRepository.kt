package com.bigcake.airquality.domain

import com.bigcake.airquality.domain.entity.AirQuality
import kotlinx.coroutines.flow.Flow

interface AirQualityRepository {
    fun getAirQualities(): Flow<Result<List<AirQuality>>>
}