package com.example.android_json

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class NavRoute(val route : String){
    object NotificationScreen: NavRoute("notification_screen")
    object SearchNotificationScreen: NavRoute("search_notification_screen")
    object DetailNotificationScreen: NavRoute("detail_notification_screen")
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: NotificationVM){
    NavHost(
        navController = navController,
        startDestination = NavRoute.NotificationScreen.route
    ){
        composable(
            NavRoute.NotificationScreen.route
        ){
            NotificationScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            NavRoute.SearchNotificationScreen.route
        ){
            SearchNotificationScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            NavRoute.DetailNotificationScreen.route + "?id={id}",
            arguments = listOf(navArgument("id"){nullable = true})
        ) {
            var id = it.arguments?.getString("id")
            if(id != null){
                DetailNotificationScreen(navController = navController, viewModel = viewModel, id.toInt())
            }
            else{
                DetailNotificationScreen(navController = navController, viewModel = viewModel)
            }

        }
    }
}