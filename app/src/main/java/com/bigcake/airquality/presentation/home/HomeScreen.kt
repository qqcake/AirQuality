package com.bigcake.airquality.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bigcake.airquality.domain.entity.AirQuality
import com.bigcake.airquality.presentation.components.AirQualityList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit,
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchAction = onNavigateToSearch)
        },
        content = { innerPadding ->
            val state = viewModel.state
            HomeContent(
                innerPaddingValues = innerPadding,
                isLoading = state.isLoading,
                lowPm25Items = state.lowPm25Items,
                highPm25Items = state.highPm25Items,
                error = state.error,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearchAction: () -> Unit,
) {
    SmallTopAppBar(
        title = { Text(text = "Air Quality") },
        actions = {
            IconButton(onClick = onSearchAction) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun HomeContent(
    innerPaddingValues: PaddingValues,
    isLoading: Boolean,
    lowPm25Items: List<AirQuality>,
    highPm25Items: List<AirQuality>,
    error: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
        when {
            isLoading -> Loading()
            error.isNotEmpty() -> ErrorMessage(error = error)
            else -> {
                AirQualityCards(airQualities = lowPm25Items)
                AirQualityList(airQualities = highPm25Items, onItemClick = {})
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(error: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun AirQualityCards(airQualities: List<AirQuality>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(airQualities, itemContent = { airQuality ->
            ElevatedCard {
                Column {
                    Row {
                        Text(
                            airQuality.siteName,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row {

                    }
                }
            }
        })
    }
}