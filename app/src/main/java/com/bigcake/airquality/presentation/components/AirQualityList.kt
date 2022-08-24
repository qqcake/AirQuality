package com.bigcake.airquality.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bigcake.airquality.presentation.model.AirQualityItemData

@Composable
fun AirQualityList(
    items: List<AirQualityItemData>,
    onItemClick: (context: Context, onClickText: String) -> Unit = { context, onClickText ->
        if (onClickText.isNotEmpty()) {
            Toast.makeText(context, onClickText, Toast.LENGTH_SHORT).show()
        }
    }
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            items = items,
            itemContent = { _, item ->
                ListItem(
                    itemData = item,
                    showIndication = item.onClickText.isNotEmpty()
                ) { onItemClick(context, item.onClickText) }
            }
        )
    }
}

@Composable
fun ListItem(
    itemData: AirQualityItemData,
    showIndication: Boolean,
    onClick: () -> Unit = {},
) {
    val modifier = Modifier
        .fillMaxSize()
        .then(if (showIndication) Modifier.clickable { onClick() } else Modifier)
        .then(Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp))
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(0.5f)) {
            Text(
                text = itemData.siteId,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = itemData.siteName)
                Text(text = itemData.county)
            }
        }
        Row(modifier = Modifier.weight(0.5f)) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = itemData.pm25,
                    modifier = Modifier.align(Alignment.End)
                )
                Text(
                    text = itemData.status,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.End)
                )
            }
            if (itemData.showArrow) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    )
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "")
                }
            } else {
                Spacer(Modifier.width(16.dp))
            }
        }
    }
    SimpleDivider(modifier = Modifier.padding(horizontal = 8.dp))
}

@Composable
@Preview
fun ListItemPreview() {
    ListItem(
        itemData = AirQualityItemData(
            "1",
            "基隆市",
            "基隆",
            "對敏感族群不健康",
            "19",
            true,
            ""
        ),
        showIndication = true
    ) {}
}