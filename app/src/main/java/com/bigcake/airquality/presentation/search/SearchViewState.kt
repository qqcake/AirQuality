package com.bigcake.airquality.presentation.search

import com.bigcake.airquality.presentation.model.AirQualityItemData

data class SearchViewState(
    val searchText: String = "",
    val searchedItems: List<AirQualityItemData> = emptyList(),
)