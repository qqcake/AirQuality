package com.bigcake.airquality.data

import com.bigcake.airquality.data.source.AirQualityRemoteSource
import com.bigcake.airquality.domain.AirQualityRepository
import com.bigcake.airquality.domain.Result
import com.bigcake.airquality.domain.entity.AirQuality
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AirQualityRepositoryImpl @Inject constructor(
    private val remoteSource: AirQualityRemoteSource
) : AirQualityRepository {
    override fun getAirQualities(): Flow<Result<List<AirQuality>>> {
        return remoteSource.getAirQualities()
    }
}