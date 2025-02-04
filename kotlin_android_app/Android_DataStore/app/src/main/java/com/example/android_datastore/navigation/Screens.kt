package com.example.android_datastore.navigation

sealed class Screens(val route: String){
    object  Login: Screens("login_screen")
    object  Home: Screens("home_screen")
    object  Setting: Screens("setting_screen")
}