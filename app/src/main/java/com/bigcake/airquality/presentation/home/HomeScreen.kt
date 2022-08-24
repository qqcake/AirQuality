package com.bigcake.airquality.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bigcake.airquality.domain.entity.AirQuality
import com.bigcake.airquality.presentation.components.AirQualityList
import com.bigcake.airquality.presentation.components.SimpleDivider
import com.bigcake.airquality.presentation.mapper.toCardData
import com.bigcake.airquality.presentation.model.AirQualityCardData
import com.bigcake.airquality.presentation.model.AirQualityItemData

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
    lowPm25Items: List<AirQualityCardData>,
    highPm25Items: List<AirQualityItemData>,
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
                SimpleDivider(modifier = Modifier.alpha(.7f))
                AirQualityList(items = highPm25Items)
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
fun AirQualityCards(airQualities: List<AirQualityCardData>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(airQualities, itemContent = { airQuality ->
            AirQualityCard(airQuality)
        })
    }
}

@Composable
fun AirQualityCard(cardData: AirQualityCardData) {
    ElevatedCard {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(cardData.siteId)
                Spacer(modifier = Modifier.width(8.dp))
                Text(cardData.siteName)
                Spacer(modifier = Modifier.width(8.dp))
                Text(cardData.pm25)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(cardData.county)
                Spacer(modifier = Modifier.width(8.dp))
                Text(cardData.status)
            }
        }
    }
}

@Composable
@Preview
fun AirQualityCardPreview() {
    AirQualityCard(
        AirQuality(
            siteId = 1,
            siteName = "基隆",
            county = "基隆市",
            status = "對敏感族群不健康",
            pm25 = 19,
            aqi = 49
        ).toCardData()
    )
}