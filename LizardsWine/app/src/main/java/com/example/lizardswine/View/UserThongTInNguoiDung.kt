package com.example.lizardswine.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.R
import com.example.lizardswine.ViewModel.LayThongTinNguoiDung

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ThongTinNguoiDung(navHostController: NavHostController, MaND: Int,viewModel: LayThongTinNguoiDung = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TaiKhoan(navHostController,MaND)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004D40),
                    titleContentColor = Color.White
                )
            )

        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DonHangSection(navHostController){
                    navHostController.navigate(Screen.DonDaMua.route + "?MaND=${MaND}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                NavigationItems(navHostController)
            }
        }
    }
}

@Composable
fun TaiKhoan(
    navHostController: NavHostController,
    maND: Int, viewModel: LayThongTinNguoiDung = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.layThongTin(maND)
    }

    val thongTin by viewModel.thongTinTheoMa.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.error.collectAsState()
    val ThongTinND = thongTin?.getOrNull()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (   verticalAlignment = Alignment.CenterVertically,)
        {
            Image(
                painter = rememberAsyncImagePainter(ThongTinND?.AnhDaiDien ?: "https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = ThongTinND?.HoTen ?: "Tên người dùng không có",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Text(
                    text = ThongTinND?.SoDienThoai ?: "Không có số điện thoại",
                    style = TextStyle(fontSize = 14.sp),
                    color = Color.White
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically,) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
                    .clickable { navHostController.navigate(Screen.CaiDat.route + "?MaND=${maND}")}
            )
            Icon(
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "Cart Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
                    .clickable {navHostController.navigate(Screen.GioHang.route) }
            )
        }


    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DonHangSection(navHostController: NavHostController, click: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE8F5E9),
        elevation = 2.dp,
        onClick = click
    ) {
        Column {
            Text(
                "Đơn hàng",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 4.dp) // Giảm padding phía dưới
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(
                    Pair(R.drawable.wallet, "Chờ xác nhận"),
                    Pair(R.drawable.box, "Chờ lấy hàng"),
                    Pair(R.drawable.layhang, "Chờ giao hàng"),
                    Pair(R.drawable.danhgia, "Đánh giá")
                ).forEach { (iconId, label) ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {

                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(8.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = label,
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationItems(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        NavigationItem(iconId = R.drawable.vidientu, label = "Ví điện tử đã liên kết"){
            navHostController.navigate(Screen.ViDienTu.route)
        }
        Divider()
        NavigationItem(iconId = R.drawable.conversation, label = "Trung tâm trợ giúp"){}
        Divider()
        NavigationItem(iconId = R.drawable.question, label = "Trò chuyện với Lizard wine"){}
    }
}
@Composable
fun NavigationItem(iconId: Int, label: String, onclick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onclick()}
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = TextStyle(fontSize = 14.sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
    }
}
