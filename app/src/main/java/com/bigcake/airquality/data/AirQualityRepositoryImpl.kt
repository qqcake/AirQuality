package com.bigcake.airquality.data

import com.bigcake.airquality.data.mapper.toDomain
import com.bigcake.airquality.data.model.AirQualityData
import com.bigcake.airquality.data.source.AirQualityLocal
import com.bigcake.airquality.data.source.AirQualityRemote
import com.bigcake.airquality.domain.AirQualityRepository
import com.bigcake.airquality.domain.Result
import com.bigcake.airquality.domain.entity.AirQuality
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

class AirQualityRepositoryImpl @Inject constructor(
    private val remoteSource: AirQualityRemote,
    private val localSource: AirQualityLocal,
) : AirQualityRepository {
    override fun getAirQualities(refresh: Boolean): Flow<Result<List<AirQuality>>> = flow {
        val data = query().firstOrNull()
        val flow = when {
            shouldFetch(refresh, data) -> {
                emit(Result.Loading(data?.toDomain()))
                try {
                    saveFetchResult(fetch())
                    query().map { Result.Success(it.toDomain()) }
                } catch (throwable: Throwable) {
                    query().map { Result.Failure(throwable.message ?: "") }
                }
            }
            else -> {
                query().map { Result.Success(it.toDomain()) }
            }
        }
        emitAll(flow)
    }

    private fun query(): Flow<List<AirQualityData>> = flow {
        emit(localSource.getAirQualities())
    }

    private fun shouldFetch(refresh: Boolean, data: List<AirQualityData>?): Boolean {
        return refresh || data.isNullOrEmpty() || localSource.isExpired(1.hours)
    }

    private suspend fun fetch(): List<AirQualityData> {
        return remoteSource.getAirQualities()
    }

    private suspend fun saveFetchResult(airQualities: List<AirQualityData>) {
        localSource.setAirQualities(airQualities)
    }
}