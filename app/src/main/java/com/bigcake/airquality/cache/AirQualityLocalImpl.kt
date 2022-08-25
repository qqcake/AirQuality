package com.bigcake.airquality.cache

import com.bigcake.airquality.cache.helper.FileHelper
import com.bigcake.airquality.cache.helper.PrefsHelper
import com.bigcake.airquality.cache.model.AirQualityObj
import com.bigcake.airquality.data.model.AirQualityData
import com.bigcake.airquality.data.source.AirQualityLocal
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class AirQualityLocalImpl @Inject constructor(
    private val moshi: Moshi,
    private val fileHelper: FileHelper,
    private val prefsHelper: PrefsHelper,
) : AirQualityLocal {
    private val adapter: JsonAdapter<List<AirQualityObj>> by lazy {
        val type = Types.newParameterizedType(List::class.java, AirQualityObj::class.java)
        moshi.adapter(type)
    }

    override suspend fun getAirQualities(): List<AirQualityData> {
        val data = fileHelper.read()
        return if (data.isEmpty()) emptyList()
        else adapter.fromJson(data)?.map(AirQualityObj::toData) ?: emptyList()
    }

    override suspend fun setAirQualities(airQualities: List<AirQualityData>) {
        val data = adapter.toJson(airQualities.toCache())
        fileHelper.write(data)
        prefsHelper.lastCacheTime = System.currentTimeMillis()
    }

    override fun isExpired(maxAge: Duration): Boolean {
        val now = System.currentTimeMillis()
        val lastCache = prefsHelper.lastCacheTime
        return (now - lastCache).milliseconds > maxAge
    }
}