package com.bigcake.airquality.domain.usecase

import com.bigcake.airquality.domain.AirQualityRepository
import com.bigcake.airquality.domain.Result
import com.bigcake.airquality.domain.entity.AirQuality
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAirQualityUseCase @Inject constructor(private val repository: AirQualityRepository) {
    operator fun invoke(refresh: Boolean): Flow<Result<List<AirQuality>>> {
        return repository.getAirQualities(refresh)
    }
}