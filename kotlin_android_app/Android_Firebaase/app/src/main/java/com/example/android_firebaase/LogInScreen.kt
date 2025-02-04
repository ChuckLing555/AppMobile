package com.example.android_firebaase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController){
    var viewModel: AccountViewModel = viewModel(modelClass = AccountViewModel::class.java)
    var state = viewModel.state
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Login") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Chat Simple",
                fontWeight =  FontWeight.ExtraBold,
                fontSize = 50.sp
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = viewModel::onChangeEmail,
                placeholder = { Text(text = "Email")},
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = viewModel::onChangePassword,
                placeholder = { Text(text = "Password")},
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                //visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    // 3 --> Chuyen sang man hinh dang nhap
                    viewModel.signIn()
                    if(state.success){
                        navController.navigate(NavRoute.Home.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(10)
            ) {
                Text(
                    text = "Login",
                    fontWeight =  FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}