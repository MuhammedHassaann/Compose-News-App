package com.muhammedhassaan.yaganews.utils

sealed class Screens(val route: String){
    object Splash: Screens(route = "splash_screen")
    object Home: Screens(route = "home_screen")
}
