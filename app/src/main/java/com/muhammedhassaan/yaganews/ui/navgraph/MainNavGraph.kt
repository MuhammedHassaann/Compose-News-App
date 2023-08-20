package com.muhammedhassaan.yaganews.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muhammedhassaan.yaganews.ui.screens.archived.ArchivedScreen
import com.muhammedhassaan.yaganews.ui.screens.details.DetailsScreen
import com.muhammedhassaan.yaganews.ui.screens.splash.AnimatedSplashScreen
import com.muhammedhassaan.yaganews.ui.screens.home.HomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screens.Splash.route){
            AnimatedSplashScreen(){
                navController.popBackStack()
                navController.navigate(Screens.Home.route)
            }
        }
        composable(route = Screens.Home.route){
            HomeScreen(){
                navController.navigate(Screens.Details.passArticleId(it))
            }
        }
        composable(
            route = Screens.Details.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            DetailsScreen()
        }
        composable(Screens.Archived.route){
            ArchivedScreen(){

            }
        }
    }
}