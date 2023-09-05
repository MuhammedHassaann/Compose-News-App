package com.muhammedhassaan.yaganews.ui.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.muhammedhassaan.yaganews.ui.navgraph.BottomNavItem
import com.muhammedhassaan.yaganews.ui.navgraph.MainNavGraph
import com.muhammedhassaan.yaganews.ui.navgraph.Screens


@Composable
fun MainScreen(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){

    val screens = listOf(
        BottomNavItem(
            Screens.Home.route,
            Screens.Home.name,
            Screens.Home.selectedIcon,
            Screens.Home.unselectedIcon
        ),
        BottomNavItem(
            Screens.Archived.route,
            Screens.Archived.name,
            Screens.Archived.selectedIcon,
            Screens.Archived.unselectedIcon
        )
    )
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    NavigationBar {
        screens.forEach{ bottomNavItem ->
            NavigationBarItem(
                selected = isSelected(currentDestination,bottomNavItem.route),
                onClick = {
                    navController.navigate(bottomNavItem.route){
                        popUpTo(Screens.Home.route)
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = bottomNavItem.name)    
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected(currentDestination,bottomNavItem.route)) bottomNavItem.selectedIcon
                        else bottomNavItem.unselectedIcon,
                        contentDescription = bottomNavItem.name
                    )
                }
            )
        }
    }
}

private fun isSelected(navDestination: NavDestination?, route: String): Boolean{
    return navDestination?.hierarchy?.any {
        it.route == route
    } == true
}