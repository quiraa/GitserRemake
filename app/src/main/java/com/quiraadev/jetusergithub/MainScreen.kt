package com.quiraadev.jetusergithub

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.screens.SplashScreen
import com.quiraadev.jetusergithub.ui.screens.detail.DetailScreen
import com.quiraadev.jetusergithub.ui.screens.detail.DetailViewModel
import com.quiraadev.jetusergithub.ui.screens.favorite.FavoriteScreen
import com.quiraadev.jetusergithub.ui.screens.favorite.FavoriteViewModel
import com.quiraadev.jetusergithub.ui.screens.home.HomeScreen
import com.quiraadev.jetusergithub.ui.screens.home.HomeViewModel
import com.quiraadev.jetusergithub.ui.screens.setting.SettingScreen
import com.quiraadev.jetusergithub.ui.screens.setting.SettingViewModel
import com.quiraadev.jetusergithub.ui.widget.BottomNavBar

@Composable
fun MainScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    settingViewModel: SettingViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    return Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Favorite.route || currentRoute == Screen.Setting.route)
                BottomNavBar(navController = navController) else null
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(route = Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    detailViewModel = detailViewModel
                )
            }
            composable(route = Screen.Favorite.route) {
                FavoriteScreen(favoriteViewModel = favoriteViewModel, navController = navController)
            }
            composable(route = Screen.Setting.route) {
                SettingScreen(settingViewModel)
            }
            composable(route = Screen.Detail.route, arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            )) {
                val username = it.arguments?.getString("username") ?: ""
                DetailScreen(
                    username = username,
                    navController = navController,
                    detailViewModel = detailViewModel
                )
            }
        }
    }
}