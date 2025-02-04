package com.example.android_api

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicListScreen(navController: NavController, viewModel: MusicViewModel){
    viewModel.getAllMusic()
    var listMusic: List<Music> = viewModel.listMusic

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Music List")
                },
                navigationIcon = {},
                actions = {
                    IconButton(
                        content = {
                            Icon(Icons.Default.Add, "Add Music")
                        },
                        onClick = {
                            navController.navigate(Screen.MusicAddScreen.route)
                        }
                    )
                }
            )
        }
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier.padding(paddingValues)
        ){
            items(listMusic){
                MusicCard(
                    music = it,
                    onUpdateClick = {
                    navController.navigate(Screen.MusicUpdateScreen.route + "?id=${it.id}") },
                    onDeleteClick = {
                        viewModel.deleteMusic(it.id)
                    }
                )
            }
        }
    }
}