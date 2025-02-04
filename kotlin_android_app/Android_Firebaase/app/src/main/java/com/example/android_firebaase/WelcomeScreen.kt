package com.example.android_firebaase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun WelcomeSceen(navController: NavHostController){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Chat Simple",
            fontWeight =  FontWeight.ExtraBold,
            fontSize = 50.sp
        )
        Button(
            onClick = {
                // 1 --> Chuyen sang man hinh dang nhap
                navController.navigate(NavRoute.Login.route)
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

        Button(
            onClick = {
                // 2 --> Chuyen sang man hinh dang ky
                navController.navigate(NavRoute.Register.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10)
        ) {
            Text(
                text = "Sign Up",
                fontWeight =  FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}