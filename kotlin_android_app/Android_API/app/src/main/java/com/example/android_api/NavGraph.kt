package com.example.android_api

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun NavGraph(navController: NavHostController, viewModel: MusicViewModel){
    NavHost(
        navController = navController,
        startDestination = Screen.MusicListScreen.route
    ) {
        composable(
            Screen.MusicListScreen.route
        ){
            MusicListScreen(navController, viewModel)
        }
        composable(
            Screen.MusicAddScreen.route
        ){
            MusicAddScreen(navController, viewModel)
        }
        composable(
            Screen.MusicUpdateScreen.route + "?id={id}",
            arguments = listOf(navArgument("id"){nullable = true})
        ){
            var id = it.arguments?.getString("id")
            if(id != null)
                MusicUpdateScreen(navController, id.toString(), viewModel)
        }
    }
}

sealed class Screen (val route: String){
    object MusicListScreen: Screen("music _list_screen")
    object MusicAddScreen: Screen("music_add_screen")
    object MusicUpdateScreen: Screen("music_update_screen")
}