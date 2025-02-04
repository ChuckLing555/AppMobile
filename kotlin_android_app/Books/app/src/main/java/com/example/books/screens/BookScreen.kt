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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.books.R


@Composable
fun BookScreen(){
    Column (
        modifier = Modifier.fillMaxSize().background( color = Color.White).padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            painter = painterResource(id = R.drawable.booklogo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "Welcome to BOOKs",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hi! Your Social Skills Seem To Need Some Polishing ",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(35.dp))
        ButtonCard("Login", {})
        Spacer(modifier = Modifier.height(12.dp))
        ButtonCard("Sign up", {})
    }
}

@Composable
fun ButtonCard(
    _value: String,
    onClickButton: () -> Unit
){
    Button(
        onClick = onClickButton,
        modifier = Modifier.fillMaxWidth(),
        colors =  if(_value.compareTo("Login") == 0) {
            ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        }
        else
            ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Blue
            ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(2.dp, color = Color.Blue)
    ) {
        Text(
            text = _value,
            Modifier.fillMaxWidth().padding(5.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}