package com.example.android_json_ontap

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String){
    object ProductScreen: Screen("product_screen")
    object ProductDetailScreen: Screen("product_detail_screen")
}

@Composable
fun NavGraph (navHostController: NavHostController, viewModel: ProductViewModel){
    NavHost(
        navController = navHostController,
        startDestination = Screen.ProductScreen.route
    ) {
        composable(
            Screen.ProductScreen.route
        ){
            ProductScreen(navHostController, viewModel)
        }

        composable(
            Screen.ProductDetailScreen.route + "?id={id}",
            arguments = listOf(navArgument("id"){nullable = true})
        ){
            var id = it.arguments?.getString("id")
            if(id != null){
                ProductDetailScreen(navHostController, viewModel, id.toInt())
            }
            else{
                ProductDetailScreen(navHostController, viewModel)
            }
        }
    }
}