package com.example.android_datastore.navigation

import androidx.collection.emptyLongSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_datastore.UserStore
import com.example.android_datastore.screen.HomeScreen
import com.example.android_datastore.screen.LoginScreen
import com.example.android_datastore.screen.SettingScreen

@Composable
fun NavGraph(navController: NavHostController){
    val context = LocalContext.current
    val store = UserStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {
        composable(route = Screens.Login.route){
            if(tokenText.value.isEmpty())
                LoginScreen(navController)
            else
                HomeScreen(navController)
        }
        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Setting.route){
            SettingScreen()
        }
    }
}