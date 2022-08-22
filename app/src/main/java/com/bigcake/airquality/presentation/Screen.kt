package com.bigcake.airquality.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object SearchScreen : Screen("search")
}
