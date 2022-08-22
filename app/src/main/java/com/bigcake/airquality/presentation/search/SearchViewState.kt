package com.bigcake.airquality.presentation.search

import com.bigcake.airquality.domain.entity.AirQuality

data class SearchViewState(
    val searchText: String = "",
    val searchedItems: List<AirQuality> = emptyList(),
)