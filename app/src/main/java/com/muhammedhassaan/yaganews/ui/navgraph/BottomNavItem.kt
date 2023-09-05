package com.muhammedhassaan.yaganews.ui.navgraph

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
