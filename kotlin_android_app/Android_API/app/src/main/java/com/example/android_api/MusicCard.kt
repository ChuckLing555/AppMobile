package com.example.android_api

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun MusicCard(
    music: Music,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit
){
    Card (
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ){
                Row (
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = music.song,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    IconButton(onClick = onUpdateClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit music"
                        )
                    }
                }

                Text(
                    text = music.lyric,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "By @${music.artist}",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "on ${convertTimeFormat(music.time)}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                IconButton(onClick = onDeleteClick, modifier = Modifier.align(Alignment.End)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete music"
                    )
                }
            }
        }
    }
}

fun convertTimeFormat(originalTime: String): String{
    val timestamp = originalTime.toLong()
    val instant = Instant.ofEpochMilli(timestamp)
    val localDateTime = LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH: mm")

    return localDateTime.format(formatter)
}