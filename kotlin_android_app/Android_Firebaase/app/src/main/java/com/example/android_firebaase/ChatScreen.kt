package com.example.android_firebaase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun ChatScreen (navController: NavHostController, email: String = ""){
    var viewModel = viewModel<ChatViewModel>(
        factory = ChatViewModelFactory(email)
    )
    var state = viewModel.state
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.friend.fullname)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
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
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            LazyColumn (
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.Bottom,
                contentPadding = PaddingValues(
                    vertical = 10.dp,
                    horizontal = 20.dp
                ),
                reverseLayout = true
            ){
                // Danh sach
                items(state.message){
                    message -> MessageBubble(
                        message.content,
                        message.sender != state.friend.email
                    )
                }
            }
            TextField(
                value = state.mess,
                onValueChange = viewModel::onChangeMassage,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.addMessage()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}