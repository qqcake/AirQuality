package com.bigcake.airquality.data.source

import com.bigcake.airquality.data.mapper.toDomain
import com.bigcake.airquality.data.remote.AirQualityService
import com.bigcake.airquality.domain.Result
import com.bigcake.airquality.domain.entity.AirQuality
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import javax.inject.Inject

class AirQualityRemoteSource @Inject constructor(
    private val airQualityService: AirQualityService
) {
    fun getAirQualities(): Flow<Result<List<AirQuality>>> = flow {
        runCatching {
            airQualityService.getAirQualities().records.map { it.toDomain() }
        }.onFailure {
            val message = when (it) {
                is SocketTimeoutException -> "Connection timeout"
                else -> it.message ?: "Unknown error"
            }
            emit(Result.Failure(message))
        }.onSuccess {
            emit(Result.Success(it))
        }
    }
}