package com.example.android_kt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBookScreen(
    navHostController: NavHostController,
    viewModel: BookViewModel,
    id: Int = -1
){
    var book by remember { mutableStateOf(viewModel.getBookById(id)) }
    var quantity by remember { mutableStateOf("1") }
    var temp: Int = 0

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text("Đặt Sách")
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {navHostController.popBackStack()}){
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        }
    ){
        Box(
            modifier = Modifier.padding(it).fillMaxSize()
        ){
            var localConfig = LocalConfiguration.current
            Box(
                modifier = Modifier.height(   (LocalConfiguration.current.screenHeightDp* 3/ 10).dp)
                    .fillMaxSize()
                    .background(color = Color.White)
            ){
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = book.imageurl,
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier.padding(top = (LocalConfiguration.current.screenHeightDp* 3/ 10).dp)
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8))
                    .background(color = Color.LightGray)
            ){
                Column (

                ){
                    Text(book.bookname)
                    Text(book.price.toString(), textAlign = TextAlign.Right)
                    Text(book.description)
                }
            }
        }
    }
}
