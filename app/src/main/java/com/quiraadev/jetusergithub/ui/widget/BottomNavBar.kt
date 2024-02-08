package com.quiraadev.jetusergithub.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.quiraadev.jetusergithub.ui.navigations.Screen

@Composable
fun BottomNavBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.map {  items ->
            NavigationBarItem(
                selected = currentRoute == items.screen.route,
                onClick = {
                    currentRoute = items.screen.route
                    navController.navigate(items.screen.route) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                            inclusive = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    val icon =
                        if (currentRoute == items.screen.route) items.selectedIcon else items.unselectedIcon
                    Icon(imageVector = icon, contentDescription = "Nav Icon")
                },
                label = {
                    Text(text = items.label)
                }
            )
        }
    }
}

data class BottomNavItems(
    val screen: Screen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val items = listOf(
    BottomNavItems(
        screen = Screen.Home,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavItems(
        screen = Screen.Favorite,
        label = "Favorite",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    ),
    BottomNavItems(
        screen = Screen.Setting,
        label = "Setting",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    ),
)