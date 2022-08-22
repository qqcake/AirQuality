package com.bigcake.airquality.di.module

import com.bigcake.airquality.data.AirQualityRepositoryImpl
import com.bigcake.airquality.domain.AirQualityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAirQualityRepository(impl: AirQualityRepositoryImpl): AirQualityRepository
}