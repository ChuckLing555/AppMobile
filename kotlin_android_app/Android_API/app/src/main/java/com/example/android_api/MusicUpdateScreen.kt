package com.example.android_api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicUpdateScreen(navController: NavController, id: String, viewModel: MusicViewModel){
    var music: Music by remember {
        mutableStateOf(Music("", "", "", "", ""))
    }
    viewModel.getMusic(id)
    music = viewModel.music

    var artist by remember { mutableStateOf("") }
    var song by remember { mutableStateOf("") }
    var lyric by remember { mutableStateOf("") }

    artist = music.artist
    song = music.song
    lyric = music.lyric

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Update Music")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.MusicListScreen.route)
                        }
                    ){
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            "Back to Music List"
                        )
                    }
                },
                actions = {}
            )
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues)
        ){
            TextField(
                value = artist,
                onValueChange = {artist = it},
                label = { Text("Artist") },
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                trailingIcon = {
                    Icon(
                        Icons.Default.Person,
                        "Artist"
                    )
                }
            )
            TextField(
                value = song,
                onValueChange = {song = it},
                label = { Text("Song") },
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                trailingIcon = {
                    Icon(
                        Icons.Default.Info,
                        "Song"
                    )
                }
            )
            TextField(
                value = lyric,
                onValueChange = {lyric = it},
                label = { Text("Lyric") },
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                trailingIcon = {
                    Icon(
                        Icons.Default.Create,
                        "Lyric"
                    )
                }
            )
            Text(
                modifier = Modifier.padding(6.dp),
                text = viewModel.musicAddResult,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Red
            )
            Row (
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(
                    onClick = {
                        artist = ""
                        song = ""
                        lyric = ""
                        viewModel.musicAddResult = ""
                    }
                ) { Text("Reset") }
                Button(
                    onClick = {
                        var updateMusic = Music(
                            artist,
                            song,
                            lyric,
                            System.currentTimeMillis().toString(),
                            music.id
                        )
                        viewModel.updateMusic(music.id, updateMusic)
                    }
                ) { Text("Save") }
            }
        }
    }
}