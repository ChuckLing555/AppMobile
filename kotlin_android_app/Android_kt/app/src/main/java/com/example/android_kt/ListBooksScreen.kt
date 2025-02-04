package com.example.android_kt

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBookScreen(
    navHostController: NavHostController,
    viewModel: BookViewModel
){
    var books by remember { mutableStateOf(viewModel.getListBook()) }
    val activity = (LocalContext.current as? Activity)
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text("Danh Mục Sách - Lê Thị Trúc Linh")
                    },
                    actions = {
                        IconButton(
                            onClick = {activity?.finishAndRemoveTask()}
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White
                    )
                )
            }
        }
    ){
        paddingValues ->
        LazyColumn (modifier = Modifier.padding(paddingValues)){
            items(books){
                BookCardScreen(
                    book = it,
                    clickToDetal = {
                        navHostController.navigate(Screen.DetailBookScreen.route + "?id=${it.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun BookCardScreen(book: Book, clickToDetal: ()-> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        onClick = clickToDetal,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = Modifier.padding(5.dp, 10.dp).width(280.dp)
            ){
                Text(
                    text = book.bookname,
                    softWrap = true
                )
                Text(
                    text = book.price.toString(),
                    color = Color.Gray
                )
            }
            AsyncImage(
                model = book.imageurl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )
        }
    }
}