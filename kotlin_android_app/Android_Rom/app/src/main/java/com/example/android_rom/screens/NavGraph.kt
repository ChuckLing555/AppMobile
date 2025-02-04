package com.example.android_rom.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class NavRoute(val route: String) {
    object List_Screen: NavRoute("contact_list")
    object Detail_Screen: NavRoute("contact_detail")
    object Search_Screen: NavRoute("contact_search")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavRoute.List_Screen.route
    ) {
        composable(
            NavRoute.List_Screen.route
        ) {
            ContactListScreen(navController = navController)
        }

        composable(
            NavRoute.Detail_Screen.route + "?id={id}",
            arguments = listOf(navArgument("id"){nullable = true})
        ) {
            var id = it.arguments?.getString("id")
            if(id != null){
                ContactDetailScreen(navController = navController, id.toInt())
            }
            else{
                ContactDetailScreen(navController = navController)
            }

        }

        composable(
            NavRoute.Search_Screen.route
        ) {
            ContactSearchScreen(navController = navController)
        }
    }
}