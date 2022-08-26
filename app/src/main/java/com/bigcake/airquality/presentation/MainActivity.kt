package com.bigcake.airquality.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bigcake.airquality.presentation.home.HomeScreen
import com.bigcake.airquality.presentation.home.HomeViewModel
import com.bigcake.airquality.presentation.search.SearchScreen
import com.bigcake.airquality.presentation.ui.theme.AirQualityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirQualityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(onNavigateToSearch = {
                                navController.navigate(Screen.SearchScreen.route)
                            })
                        }
                        composable(route = Screen.SearchScreen.route) { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry(Screen.HomeScreen.route)
                            }
                            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)
                            SearchScreen(
                                navController = navController,
                                homeViewModel = homeViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}