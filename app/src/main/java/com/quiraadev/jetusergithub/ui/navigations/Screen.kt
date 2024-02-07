package com.quiraadev.jetusergithub.ui.navigations

import androidx.navigation.NavHostController

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Favorite : Screen("favorite")
    data object Setting : Screen("setting")
    data object Detail : Screen("home/{username}"){
        fun createRoute(username: String) = "home/$username"
    }

    companion object {
        fun push(navController: NavHostController, screen: Screen) {
            navController.navigate(screen.route)
        }

        fun pushAndReplace(navController: NavHostController, screen: Screen) {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }

        fun pop(navController: NavHostController) {
            navController.popBackStack()
        }
    }
}