package com.bigcake.airquality.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bigcake.airquality.presentation.components.AirQualityList
import com.bigcake.airquality.presentation.components.SimpleDivider
import com.bigcake.airquality.presentation.home.HomeViewModel
import com.bigcake.airquality.presentation.model.AirQualityItemData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
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
                },
                onNavigationBack = {
                    navController.popBackStack()
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
    onSearchTextChange: (String) -> Unit,
    onNavigationBack: () -> Unit = {},
) {
    SmallTopAppBar(
        title = {
            BottomOutlineTextField(
                value = searchText,
                placeholder = placeHolderText,
                onValueChange = onSearchTextChange,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun BottomOutlineTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    textColor: Color = Color.Unspecified
) {
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        singleLine = true,
        maxLines = 1,
        textStyle = MaterialTheme.typography.titleMedium.copy(color = textColor),
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.alpha(.7f)
                    )
                }
            }
            innerTextField()
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
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
    searchedItems: List<AirQualityItemData>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
        SimpleDivider(modifier = Modifier.fillMaxWidth())
        if (searchedItems.isNotEmpty()) {
            AirQualityList(items = searchedItems)
        } else if (searchText.isNotEmpty()) {
            Notice(searchText = "Cannot find air quality for site named '$searchText'")
        } else {
            Notice(searchText = "Input site name for air quality information")
        }
    }
}

@Composable
fun Notice(searchText: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = searchText)
    }
}