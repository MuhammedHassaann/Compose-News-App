package com.muhammedhassaan.yaganews.screens.navgraph

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muhammedhassaan.yaganews.screens.AnimatedSplashScreen
import com.muhammedhassaan.yaganews.screens.HomeScreen
import com.muhammedhassaan.yaganews.utils.Screens

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Screens.Splash.route){
            AnimatedSplashScreen(){
                navController.popBackStack()
                navController.navigate(Screens.Home.route)
            }
        }
        composable(Screens.Home.route){
            HomeScreen()
        }
    }
}