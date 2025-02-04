package com.example.books.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.books.R

@Composable
fun LoginScreen(){
    Column (
        modifier = Modifier.fillMaxSize().background( color = Color.White).padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.loginlogo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "LOGIN",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(
                    text = "User Name or Email"
                )
            },
            shape = RoundedCornerShape(30)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(
                    text = "Password"
                )
            },
            shape = RoundedCornerShape(30)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Forgot Password",
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth().align(Alignment.Start).padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
            ),
            modifier = Modifier.width(200.dp).padding(start = 50.dp, end = 15.dp).align(AbsoluteAlignment.Right),
            shape = RoundedCornerShape(25.dp),
            border = BorderStroke(2.dp, color = Color.Blue)
        ) {
            Text(
                text = "Login",
                Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}