package com.bigcake.airquality.cache.helper

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PrefsHelper @Inject constructor(context: Context) {
    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = prefs.getLong(PREFS_KEY_LAST_CACHE_MILLIS, 0)
        set(lastCache) = prefs.edit().putLong(PREFS_KEY_LAST_CACHE_MILLIS, lastCache).apply()

    companion object {
        const val SHARED_PREFS_NAME = "air_quality_prefs"
        const val PREFS_KEY_LAST_CACHE_MILLIS = "last_cache_millis"
    }
}