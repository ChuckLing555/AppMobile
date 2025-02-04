package com.example.android_firebaase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavHostController){
    // danh sach tam
    var viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    var state = viewModel.state
    var lst = mutableListOf<User>()
    lst.add(User("abc@gmail.com","123","Khuong Tu Nha"))
    lst.add(User("TonNgoKhong@gmail.com","123","Ton Ngo Khong"))
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Danh Sach") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = {
                            // 5 -->
                            viewModel.SignOut()
                            navController.navigate(NavRoute.Welcome.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn (
            modifier = Modifier
                .padding(it)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            if(state.contactList.count() > 0){
                items(state.contactList){
                    CardInfo(
                        user = it,
                        {
                            navController.navigate(
                                NavRoute.Chat.route + "?email=${it.email}"
                            )
                        }
                    )
                }
            }
            else{
                item { Text(text = "Khong co danh sach") }
            }
        }
    }
}