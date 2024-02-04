package com.quiraadev.jetusergithub.ui.navigations

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: Screen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
