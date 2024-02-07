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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.ui.navigations.Screen

@Composable
fun BottomNavBar(
    navController: NavHostController
) {
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, items ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    Screen.pushAndReplace(navController, items.screen)
                },
                icon = {
                    val icon =
                        if (selectedItemIndex == index) items.selectedIcon else items.unselectedIcon
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