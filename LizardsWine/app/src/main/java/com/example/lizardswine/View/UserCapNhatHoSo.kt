package com.example.lizardswine.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.lizardswine.Api.ThongTinND
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.R
import com.example.lizardswine.ViewModel.LayThongTinNguoiDung

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuaHoSo(navHostController: NavHostController, MaND:Int, viewModel: LayThongTinNguoiDung = viewModel()) {
    LaunchedEffect (Unit){
        viewModel.layThongTin(MaND)
    }
    val thongtin by viewModel.thongTinTheoMa.collectAsState()
    val ThongTinND = thongtin?.getOrNull()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sửa hồ sơ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
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
    ){innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {
                Image(
                    painter = rememberAsyncImagePainter(ThongTinND?.AnhDaiDien?:"https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera), // Replace with your camera icon
                        contentDescription = "Change Avatar",
                        tint = Color(0xFF004D40)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            ProfileInfoRow(title = "Tên người dùng", value = ThongTinND?.HoTen?:"", isEditable = true){
                navHostController.navigate(Screen.SuaTen.route + "?MaND=${MaND}")
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfoRow(title = "Điện thoại", value = ThongTinND?.SoDienThoai?:"", isEditable = true){
                navHostController.navigate(Screen.SuaSDT.route)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfoRow(title = "Email", value = ThongTinND?.Email?:"", isEditable = true){}
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfoRow(title = "Mật khẩu", value = inputString(ThongTinND?.MatKhau?:""), isEditable = true){
                navHostController.navigate(Screen.SuaMatKhau.route)
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfoRow(title = "Giới tính", value = "Nam", isEditable = true){}
            Spacer(modifier = Modifier.height(8.dp))
            ProfileInfoRow(title = "Ngày sinh", value = ThongTinND?.NgaySinh?:"", isEditable = true){}
        }
    }
}
fun inputString(input: String): String{
    val length = 8.coerceAtMost(input.length)
    return "*".repeat(length)
}
@Composable
fun ProfileInfoRow(title: String, value: String, isEditable: Boolean, click: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
            .clickable(enabled = isEditable) { click() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        if (isEditable) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Edit",
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
