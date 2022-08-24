package com.bigcake.airquality.presentation.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleDivider(modifier: Modifier = Modifier) {
    Divider(
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}