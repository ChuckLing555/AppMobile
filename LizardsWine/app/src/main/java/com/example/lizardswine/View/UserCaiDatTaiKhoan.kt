package com.example.lizardswine.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lizardswine.Data.UserStore
import com.example.lizardswine.Navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaiDatTaiKhoan(navHostController: NavHostController, MaND: Int) {

    val context = LocalContext.current
    val store = UserStore(context)
    val user = store.getUser().collectAsState(initial = null)


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cài Đặt Tài Khoản",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2E1F) // Màu xanh đậm
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF5F5F5)) // Màu nền sáng
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Danh mục "Tài khoản"
                Text(
                    text = "Tài khoản",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                CaiDatOptionItem("Tài khoản & Bảo mật"){
                    navHostController.navigate(Screen.TaiKhoanVaBaoMat.route + "?MaND=${MaND}")
                }
                CaiDatOptionItem("Địa chỉ"){
                    navHostController.navigate(Screen.DSDiaChi.route + "?MaND=${MaND}")
                }
                CaiDatOptionItem("Ví điện tử"){
                    navHostController.navigate(Screen.ViDienTu.route)
                }

                // Danh mục "Cài đặt"
                Text(
                    text = "Cài đặt",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
                CaiDatOptionItem("Ngôn ngữ"){
                    navHostController.navigate(Screen.NgonNgu.route)
                }

                Spacer(modifier = Modifier.weight(1f))

                // Nút "Đăng xuất"
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                        store.logoutUser()
                    }
                        navHostController.navigate(Screen.DangNhap.route ) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp).height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A2E1F)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Đăng xuất",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CaiDatOptionItem(title: String, onClickCard: () -> Unit) {
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp)),
        onClick = onClickCard
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }

}