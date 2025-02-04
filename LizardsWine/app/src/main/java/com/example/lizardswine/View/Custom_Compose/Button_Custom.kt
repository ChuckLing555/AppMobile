package com.example.lizardswine.View.Custom_Compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lizardswine.R

@Composable
fun ButtonAccept_Cancel(
    onClickButton: () -> Unit,
    colorText_Border: Long,
    text: String
){
    Button(
        onClick = onClickButton,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8F5E9)),//0xFFE8F5E9
        border = BorderStroke(1.dp, Color(colorText_Border)),//0xFF009688
    ) {
        Text(text = text, color = Color(colorText_Border))
    }
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF004D40),
        //modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.home),
                contentDescription = "Home", Modifier.size(25.dp).padding(bottom = 3.dp)) },
            label = { Text(text = "Trang chủ", style = TextStyle(fontSize = 13.sp)) },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.history),
                contentDescription = "History", Modifier.size(25.dp).padding(bottom = 3.dp)) },
            label = { Text(text = "Lịch sử", style = TextStyle(fontSize = 13.sp)) },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.bell),
                contentDescription = "Bell", Modifier.size(25.dp).padding(bottom = 3.dp)) },
            label = { Text(text = "Thông báo", style = TextStyle(fontSize = 13.sp)) },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.user),
                contentDescription = "User", Modifier.size(25.dp).padding(bottom = 3.dp)) },
            label = { Text(text = "Tài khoản", style = TextStyle(fontSize = 13.sp)) },
            selected = true,
            onClick = {}
        )
    }
}