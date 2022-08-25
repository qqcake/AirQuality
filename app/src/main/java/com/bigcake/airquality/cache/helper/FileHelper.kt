package com.bigcake.airquality.cache.helper

import android.content.Context
import javax.inject.Inject

class FileHelper @Inject constructor(
    private val context: Context
) {
    fun write(data: String) {
        context.openFileOutput(FILE_CACHE_NAME, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    fun read() = runCatching {
        var result: String
        context.openFileInput(FILE_CACHE_NAME).use { fis ->
            fis.bufferedReader().use {
                result = it.readText()
            }
        }
        result
    }.getOrDefault("")

    companion object {
        private const val FILE_CACHE_NAME = "air_quality_cache"
    }
}