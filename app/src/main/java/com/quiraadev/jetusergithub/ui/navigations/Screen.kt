package com.quiraadev.jetusergithub.ui.navigations

sealed class Screen(val route: String) {
    data object Home : Screen("ome")
    data object Detail : Screen("detail")
    data object Splash : Screen("splash")
    data object Setting : Screen("setting")
    data object Favorite : Screen("favorite")
}