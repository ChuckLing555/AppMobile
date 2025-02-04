package com.example.lizardswine.View

import com.example.lizardswine.View.Custom_Compose.CardDiaChiNguoiDung


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lizardswine.Model.DiaChi
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.View.Custom_Compose.DSDiaChiNguoiDung
import com.example.lizardswine.ViewModel.HoaDonViewModel
import com.example.lizardswine.ViewModel.LayThongTinNguoiDung

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSDiaChi(navHostController: NavHostController, MaND : Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Địa chỉ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF003D24))
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    navHostController.navigate(Screen.CapNhatDiaChi.route + "?MaND=${MaND}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp).navigationBarsPadding(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF188158), // Màu xanh đậm
                    contentColor = Color.White // Màu chữ trắng
                ),
                shape = RoundedCornerShape(8.dp) // Bo góc
            ) {
                Text(
                    text = "Thêm địa chỉ mới",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                item {
                    DSDiaChiNguoiDung(MaND)
                }
            }
            Spacer(modifier = Modifier.weight(1f)) // Đẩy nút xuống cuối màn hình
        }
    }
}

