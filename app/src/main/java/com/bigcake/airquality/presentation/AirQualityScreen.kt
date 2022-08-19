package com.bigcake.airquality.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bigcake.airquality.domain.entity.AirQuality

@Composable
fun AirQualityScreen(viewModel: AirQualityViewModel = hiltViewModel()) {
    val state = viewModel.state
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onGenerateButtonClicked() }) {
                Text(text = "Add New Item")
            }
            AirQualityCards(airQualities = state.lowPm25Items)
            AirQualityList(airQualities = state.highPm25Items, onItemClick = {})
        }
        if (viewModel.state.error.isNotBlank()) {
            Text(
                text = viewModel.state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
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

@Composable
fun AirQualityList(
    airQualities: List<AirQuality>,
    onItemClick: () -> Unit,
) {
    rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(airQualities, itemContent = { airQuality ->
            ListItem(airQuality = airQuality, onClick = onItemClick)
        })
//        if (filterText.isNotEmpty() && items.isEmpty()) {
//            item { NoResultNotice(loginFilterText = filterText) }
//        }
    }
}

@Composable
fun ListItem(airQuality: AirQuality, onClick: () -> Unit) {
    Row(modifier = Modifier.clickable { onClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    airQuality.siteName,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
//            Text(
//                text = user.id.toString(),
//                style = MaterialTheme.typography.bodyMedium
//            )
        }
    }
}

@Composable
fun NoResultNotice(loginFilterText: String) {
    Text(
        text = "Login '$loginFilterText' not found",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}