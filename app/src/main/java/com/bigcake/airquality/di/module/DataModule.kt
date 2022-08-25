package com.bigcake.airquality.di.module

import com.bigcake.airquality.cache.AirQualityLocalImpl
import com.bigcake.airquality.data.AirQualityRepositoryImpl
import com.bigcake.airquality.data.source.AirQualityLocal
import com.bigcake.airquality.data.source.AirQualityRemote
import com.bigcake.airquality.domain.AirQualityRepository
import com.bigcake.airquality.network.AirQualityRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindAirQualityRepository(impl: AirQualityRepositoryImpl): AirQualityRepository

    @Singleton
    @Binds
    abstract fun bindAirQualityRemote(impl: AirQualityRemoteImpl): AirQualityRemote

    @Singleton
    @Binds
    abstract fun bindAirQualityLocal(impl: AirQualityLocalImpl): AirQualityLocal
}