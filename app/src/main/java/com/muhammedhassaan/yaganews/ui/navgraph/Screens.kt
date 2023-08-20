package com.muhammedhassaan.yaganews.ui.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home


private const val ARTICLE_DETAILS_ID = "id"

sealed class Screens(val route: String) {
    object Splash : Screens(route = "splash_screen")
    object Home : Screens(route = "home_screen") {
        const val name = "Name"
        val selectedIcon = Icons.Filled.Home
        val unselectedIcon = Icons.Outlined.Home
    }

    object Archived : Screens(route = "archived_screen") {
        const val name = "Archived"
        val selectedIcon = Icons.Filled.Favorite
        val unselectedIcon = Icons.Outlined.FavoriteBorder
    }

    object Details : Screens(route = "details_screen/{$ARTICLE_DETAILS_ID}") {
        fun passArticleId(articleId: Int): String {
            return Details.route.replace(
                oldValue = "{$ARTICLE_DETAILS_ID}",
                newValue = articleId.toString()
            )
        }
    }
}
