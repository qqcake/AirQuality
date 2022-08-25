package com.bigcake.airquality.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AirQualitiesDto(
    @Json(name = "__extras")
    val extras: Extras,
    @Json(name = "fields")
    val fields: List<Field>,
    @Json(name = "include_total")
    val includeTotal: Boolean,
    @Json(name = "limit")
    val limit: String,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "offset")
    val offset: String,
    @Json(name = "records")
    val records: List<Record>,
    @Json(name = "resource_format")
    val resourceFormat: String,
    @Json(name = "resource_id")
    val resourceId: String,
    @Json(name = "total")
    val total: String
) {
    @JsonClass(generateAdapter = true)
    data class Extras(
        @Json(name = "api_key")
        val apiKey: String
    )

    @JsonClass(generateAdapter = true)
    data class Field(
        @Json(name = "id")
        val id: String,
        @Json(name = "info")
        val info: Info,
        @Json(name = "type")
        val type: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Info(
            @Json(name = "label")
            val label: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "next")
        val next: String,
        @Json(name = "start")
        val start: String
    )

    @JsonClass(generateAdapter = true)
    data class Record(
        @Json(name = "aqi")
        val aqi: String,
        @Json(name = "co")
        val co: String,
        @Json(name = "co_8hr")
        val co8hr: String,
        @Json(name = "county")
        val county: String,
        @Json(name = "latitude")
        val latitude: String,
        @Json(name = "longitude")
        val longitude: String,
        @Json(name = "no")
        val no: String,
        @Json(name = "no2")
        val no2: String,
        @Json(name = "nox")
        val nox: String,
        @Json(name = "o3")
        val o3: String,
        @Json(name = "o3_8hr")
        val o38hr: String,
        @Json(name = "pm10")
        val pm10: String,
        @Json(name = "pm10_avg")
        val pm10Avg: String,
        @Json(name = "pm2.5")
        val pm25: String,
        @Json(name = "pm2.5_avg")
        val pm25Avg: String,
        @Json(name = "pollutant")
        val pollutant: String,
        @Json(name = "publishtime")
        val publishtime: String,
        @Json(name = "siteid")
        val siteid: String,
        @Json(name = "sitename")
        val sitename: String,
        @Json(name = "so2")
        val so2: String,
        @Json(name = "so2_avg")
        val so2Avg: String,
        @Json(name = "status")
        val status: String,
        @Json(name = "wind_direc")
        val windDirec: String,
        @Json(name = "wind_speed")
        val windSpeed: String
    )
}