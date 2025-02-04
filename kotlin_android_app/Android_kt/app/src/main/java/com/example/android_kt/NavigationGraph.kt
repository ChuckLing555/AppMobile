package com.example.android_kt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String){
    object ListBookScreen: Screen("list_book_screen")
    object DetailBookScreen: Screen("detail_book_screen")
}

@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModel: BookViewModel
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.ListBookScreen.route
    ) {
        composable(
            Screen.ListBookScreen.route
        ){
            ListBookScreen(navHostController, viewModel)
        }

        composable(
            Screen.DetailBookScreen.route + "?id={id}",
            arguments = listOf(navArgument("id"){nullable = true})
        ){
            var id = it.arguments?.getString("id")
            if(id != null){
                DetailBookScreen(navHostController, viewModel, id.toInt())
            }
            else DetailBookScreen(navHostController, viewModel)
        }
    }
}