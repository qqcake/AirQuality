package com.bigcake.airquality.network.api

import com.bigcake.airquality.BuildConfig
import com.bigcake.airquality.network.model.AirQualitiesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityService {
    @GET("aqx_p_432")
    suspend fun getAirQualities(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): AirQualitiesDto
}