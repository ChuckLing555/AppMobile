package com.example.lizardswine.View

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.lizardswine.Model.Anh
import com.example.lizardswine.R
import java.text.DecimalFormat
import com.example.lizardswine.Model.ChiTietSanPham
import com.example.lizardswine.Model.Ruou
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.View.Custom_Compose.CardChiTietThanhToan
import com.example.lizardswine.View.Custom_Compose.CardDSRuouTheoDanhMuc
import com.example.lizardswine.View.Custom_Compose.CardDanhGiaRuou
import com.example.lizardswine.View.Custom_Compose.CardDiaChi
import com.example.lizardswine.View.Custom_Compose.CardPTThanhToan
import com.example.lizardswine.View.Custom_Compose.CardThongTinRuou
import com.example.lizardswine.ViewModel.RuouViewModel


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChiTietSanPham(navHostController: NavHostController,maR: Int, viewModel: RuouViewModel = viewModel()) {
    val formatter = DecimalFormat("#,###")
    val ruou by viewModel.ruouTheoMa.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()
    val ruouData = ruou?.getOrNull()
    val dsRuou = viewModel.tatCaRuouList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.layChiTietRuouTheoMaR(maR)
        viewModel.layTatCaRuou()
    }

    val listState = rememberLazyListState()
    val maxAlphaScroll = 300f // Khoảng cách cuộn tối đa để đạt alpha = 1

    val alpha by derivedStateOf {
        val scrollOffset = listState.firstVisibleItemScrollOffset.toFloat()
        if (scrollOffset >= maxAlphaScroll) {
            1f // Giữ alpha ở 1f khi cuộn vượt ngưỡng
        } else {
            (scrollOffset / maxAlphaScroll).coerceIn(0f, 1f)
        }
    }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(1f), // Đặt TopAppBar lên trên
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            Color(0xFF0A2E1F).copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().clickable {
                            navHostController.popBackStack()
                        }
                    )
                }
                Row(modifier = Modifier.padding(10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color(0xFF0A2E1F).copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color(0xFF0A2E1F).copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cart),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize().clickable {
                                navHostController.navigate(Screen.GioHang.route)
                            }
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF004D40).copy(alpha = alpha),
            titleContentColor = Color.White
        )
    )


    Scaffold(
        bottomBar = { ButtonBarChiTietRuou(navHostController) }
    ) { paddingValues ->
        var soLuongSP  by remember{ mutableStateOf(1) }
        val formatter = DecimalFormat("#,###")
        //val giaBan = formatter.format(ruouData?.GiaBan)
        Box(modifier = Modifier.fillMaxSize()) {
            when{
                isLoading.value -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFF0A2E1F)
                    )
                }
                ruou != null -> {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(Color.White)
                    ) {
                        item {
                            if (ruouData != null) {
                                Image(
                                    painter = rememberAsyncImagePainter(ruouData.AnhRuou[0].AnhRuou),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(460.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = ruouData?.TenRuou ?: "",
                                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                                    color = Color(0xFF0A2E1F)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                                    Text(
                                        text = "${ruouData?.GiaBan?.let { formatter.format(it.toDouble()) }} VND",
                                        style = TextStyle(fontSize = 20.sp,  color = Color(0xFF0A2E1F))
                                    )
                                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically){
                                        Text ("${ruouData?.DiemDG}/5 ",  color = Color.Gray)
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Star",
                                            tint = Color(0xFFFFD700),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Số lượng tồn: ${ruouData?.SoLuongTon}",
                                            style = MaterialTheme.typography.body2,
                                            color = Color.Gray
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Divider(
                                    color = Color.LightGray,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row{
                                    Text(text = "Xuất xứ: ", color = Color.Gray)
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(text = "${ruouData?.XuatXu}", color = Color.Gray)
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_wine),
                                            contentDescription = null,
                                            tint = Color.Unspecified, // Vô hiệu hóa tint để giữ nguyên màu gốc
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(text = "${ruouData?.NamSanXuat}", color = Color.Gray)
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Row{
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_wine_alcohol_degree),
                                            contentDescription = null,
                                            tint = Color.Unspecified, // Vô hiệu hóa tint để giữ nguyên màu gốc
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(text = "${ruouData?.NongDoCon} %", color = Color.Gray)
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Row{
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_wine_bottle_size),
                                            contentDescription = null,
                                            tint = Color.Unspecified, // Vô hiệu hóa tint để giữ nguyên màu gốc
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(text = "${ruouData?.DungTich} ml", color = Color.Gray)
                                    }
                                }
                            }
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "Mô tả:",
                                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                    color = Color(0xFF0A2E1F)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${ruouData?.MoTa}",
                                    style = TextStyle(fontSize = 16.sp),
                                    lineHeight = 20.sp,
                                    color = Color(0xFF0A2E1F)
                                )
                            }
                        }
                        item {
                            Row (
                                modifier = Modifier.fillMaxWidth().padding(top = 0.dp).padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Text("Số lượng:", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0A2E1F))
                                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
                                    IconButton(onClick = {
                                        if(soLuongSP > 1) {
                                            soLuongSP--
                                        }
                                    }, modifier = Modifier.padding(horizontal = 20.dp)){
                                        Icon(
                                            painter = painterResource(id = R.drawable.minus),
                                            contentDescription = null,
                                            tint = Color(0xFF0A2E1F),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Text(soLuongSP.toString(),  fontSize = 20.sp,  color = Color(0xFF0A2E1F))
                                    IconButton(onClick = {
                                        if(soLuongSP >= 1 ) {
                                            soLuongSP++
                                        }
                                    }, modifier = Modifier.padding(start = 20.dp)){
                                        Icon(
                                            painter = painterResource(id = R.drawable.add),
                                            contentDescription = null,
                                            tint = Color(0xFF0A2E1F),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "ĐÁNH GIÁ SẢN PHẨM", fontWeight = FontWeight.Bold, fontSize = 22.sp,
                                    color = Color(0xFF0A2E1F),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Star",
                                        tint = Color(0xFFFFD700),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "${ruouData?.DiemDG}/5 (200 bài đánh giá)",
                                        style = MaterialTheme.typography.body2,
                                        color = Color.Gray
                                    )
                                }

                                CardDanhGiaRuou()

                                Text(
                                    text = "Xem tất cả >>",fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0A2E1F),
                                    modifier = Modifier.align(Alignment.End).clickable(onClick = {
                                        navHostController.navigate(Screen.XemDSDanhGia.route)
                                    })
                                )
                            }
                        }
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth().padding(8.dp)
                            ) {
                                Divider(
                                    color = Color(0xFF0A2E1F),
                                    thickness = 1.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Một số sản phẩm khác",color = Color(0xFF0A2E1F), fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                                Divider(
                                    color = Color(0xFF0A2E1F),
                                    thickness = 1.dp,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        item{
                            CardDSRuouTheoDanhMuc(dsRuou,navHostController)
                        }
                    }
                }
                message.value != null -> {
                    androidx.compose.material.Text(
                        text = (message.value) ?: " Error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    androidx.compose.material.Text(
                        text = "OMG! Api có vấn đề.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }
    }
}

@Composable
fun ButtonBarChiTietRuou(navHostController: NavHostController){
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
                modifier = Modifier.clickable {},
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = null,
                    tint = Color(0xFF004D40),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Thêm vào giỏ hàng", color = Color(0xFF004D40))
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

