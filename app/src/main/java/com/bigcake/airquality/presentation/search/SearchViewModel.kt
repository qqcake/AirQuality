package com.bigcake.airquality.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bigcake.airquality.domain.entity.AirQuality
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(SearchViewState())
        private set

    fun onSearchTextChange(allItems: List<AirQuality>, searchText: String) {
        val searchedItems =
            if (searchText.isNotEmpty()) allItems.filter { it.siteName.contains(searchText) }
            else emptyList()
        state = state.copy(
            searchText = searchText,
            searchedItems = searchedItems
        )
    }
}
