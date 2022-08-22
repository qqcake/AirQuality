package com.bigcake.airquality.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bigcake.airquality.domain.entity.AirQuality
import com.bigcake.airquality.presentation.components.AirQualityList
import com.bigcake.airquality.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel
) {
    Scaffold(
        topBar = {
            SearchTopBar(
                searchText = viewModel.state.searchText,
                placeHolderText = "Search air quality by site name",
                onSearchTextChange = {
                    viewModel.onSearchTextChange(homeViewModel.state.allItems, it)
                }
            )
        },
        content = { innerPadding ->
            SearchContent(
                innerPaddingValues = innerPadding,
                searchText = viewModel.state.searchText,
                searchedItems = viewModel.state.searchedItems
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    searchText: String,
    placeHolderText: String,
    onSearchTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.background(Color.Red)) {
        SmallTopAppBar(
            title = {},
            actions = {
                OutlinedTextField(
                    value = searchText,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    ),
                    onValueChange = onSearchTextChange,
                    placeholder = { Text(placeHolderText) },
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(50)
                        )
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Back to Home"
                    )
                }
            }
        )
        Divider(modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(searchText = "", placeHolderText = "place holder", onSearchTextChange = {})
}


@Composable
fun SearchContent(
    innerPaddingValues: PaddingValues,
    searchText: String,
    searchedItems: List<AirQuality>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
        if (searchedItems.isNotEmpty()) {
            AirQualityList(airQualities = searchedItems, onItemClick = {})
        } else if (searchText.isNotEmpty()) {
            Notice(searchText = "Cannot find air quality for site named '$searchText'")
        } else {
            Notice(searchText = "Input site name for air quality information")
        }
    }
}

@Composable
fun Notice(searchText: String) {
    Text(
        text = searchText,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}