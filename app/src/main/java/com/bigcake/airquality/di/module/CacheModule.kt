package com.bigcake.airquality.di.module

import android.content.Context
import com.bigcake.airquality.cache.helper.FileHelper
import com.bigcake.airquality.cache.helper.PrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
    @Singleton
    @Provides
    fun provideFileHelper(@ApplicationContext context: Context): FileHelper {
        return FileHelper(context)
    }

    @Singleton
    @Provides
    fun providePrefsHelper(@ApplicationContext context: Context): PrefsHelper {
        return PrefsHelper(context)
    }
}