package com.bigcake.airquality.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bigcake.airquality.domain.entity.AirQuality

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