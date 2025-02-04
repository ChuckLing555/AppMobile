package com.example.lizardswine.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GioHang(navHostController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Giỏ hàng",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004D40)
                ),
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF004D40))
                    .navigationBarsPadding()
                    .padding(0.dp).padding(top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RectangleShape, //Xóa border,
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "Tổng thành tiền:", color = Color(0xFF004D40))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "15000000", color = Color(0xFF004D40))
                    }
                }
                Button(
                    onClick = {
                        navHostController.navigate(Screen.ThanhToan.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40)),
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                ) {
                    Text(text = "Mua ngay", color = Color.White)
                }
            }
        }

        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = { }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tất cả",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            items(3){index ->
                CartItem()
            }

        }
    }
}

@Composable
fun CartItem() {
    androidx.compose.material.Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        backgroundColor = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = { },
            )
            Image(
                painter = painterResource(id = R.drawable.anhchairuou),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 6.dp)
                    .clip(RoundedCornerShape(2.dp)),
                contentScale = ContentScale.Crop
            )

            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Rượu pháp nhập mỹ thuộc",
                    fontSize = 16.sp,
                    color = Color(0xFF004D40),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "888.000",
                    fontSize = 15.sp,
                    color = Color(0xFF004D40)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease",
                            tint = Color(0xFF004D40)
                        )
                    }
                    Text(
                        text = "1",
                        fontSize = 16.sp,
                        color = Color(0xFF004D40)
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase",
                            tint = Color(0xFF004D40)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Xóa",
                        fontSize = 14.sp,
                        color = Color.Red,
                        modifier = Modifier.clickable { }
                    )
                }
            }

        }
    }
}
