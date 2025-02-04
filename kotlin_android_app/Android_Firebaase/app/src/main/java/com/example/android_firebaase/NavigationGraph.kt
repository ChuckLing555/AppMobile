package com.example.android_firebaase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

sealed class NavRoute(val route: String){
    object Welcome: NavRoute("welcome_screen")
    object Login: NavRoute("login_screen")
    object Register: NavRoute("register_screen")
    object Home: NavRoute("home_screen")
    object Chat: NavRoute("chat_screen")
}

@Composable
fun NavgationGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = if(Firebase.auth.currentUser != null){
            NavRoute.Home.route
        } else NavRoute.Welcome.route
    )
    {
        composable(NavRoute.Welcome.route) {
            WelcomeSceen(navController)
        }
        composable(NavRoute.Register.route) {
            RegisterScreen(navController)
        }
        composable(NavRoute.Login.route) {
            LoginScreen(navController)
        }
        composable(NavRoute.Home.route) {
            ContactListScreen(navController)
        }
        composable(
            NavRoute.Chat.route + "?email={email}",
            arguments = listOf(navArgument("email"){nullable = false})
        ) {
            var email = it.arguments?.getString("email")
            ChatScreen(navController = navController, email?:"")
        }

    }
}