package com.example.lizardswine.View.Custom_Compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Navigation.NavItem
import com.example.lizardswine.View.Custom_Compose.CardChiTietThanhToan
import com.example.lizardswine.View.Custom_Compose.CardDiaChi
import com.example.lizardswine.View.Custom_Compose.CardHoaDon
import com.example.lizardswine.View.Custom_Compose.CardPTThanhToan
import com.example.lizardswine.View.Custom_Compose.CardThongTinRuou
import com.example.lizardswine.View.Custom_Compose.CardThongTinRuouND
import com.example.lizardswine.ViewModel.HoaDonViewModel
import com.example.lizardswine.ViewModel.LayHoaDonNguoiDung
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChiTietDonHangND(navHostController: NavHostController, MaHD: Int) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Chi tiết hóa đơn",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navHostController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF009688)
                )
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .navigationBarsPadding()
        ) {
            ChiTietND(MaHD)
        }
    }
}

@Composable
fun ChiTietND( MaHD: Int, viewModel: LayHoaDonNguoiDung = viewModel()) {

    val hoadonND by viewModel.hoaDon.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()
    val hoaDonData = hoadonND?.getOrNull()

    val formatter = DecimalFormat("#,###")


    LaunchedEffect(Unit) {
        viewModel.layChiTietHoaDonTheoMaHDND(MaHD)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when{
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0A2E1F)
                )
            }
            hoaDonData != null -> {
                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)

                ) {
                    item { CardDiaChi(hoadon = hoadonND) {} }
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp) ,
                            backgroundColor = Color(0xFFE8F5E9),
                        ) {
                            Column (modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min).padding(10.dp))
                            {
                                Column{
                                    hoaDonData?.ChiTietHoaDon?.forEach{
                                            ruou -> CardThongTinRuouND (ruou)
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }


                                Divider(
                                    color = Color.LightGray,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(vertical = 6.dp).padding(8.dp)
                                )

                                Row (modifier = Modifier.fillMaxWidth().padding(10.dp), Arrangement.End)
                                {
                                    Text(
                                        text = "Thành tiền: ${hoaDonData?.TongTien?.let { formatter.format(it.toDouble()) }?: "N/A"}đ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                }
                            }
                        }

                    }

                    item { Spacer(modifier = Modifier.height(16.dp))}
                    item { CardPTThanhToan(hoadon = hoadonND)}
                    item { Spacer(modifier = Modifier.height(16.dp))}
                    item { CardChiTietThanhToan(hoadon = hoadonND)}
                    item { Spacer(modifier = Modifier.height(16.dp))}
                    item {
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE8F5E9)),//0xFFE8F5E9
                            border = BorderStroke(1.dp, Color(0xFF009688)),//0xFF009688
                        ) {
                            hoaDonData?.LoaitrangThai?.let { Text(text = it, color = Color(0xFF009688)) }
                        }
                    }
                }
            }
            message.value != null -> {
                Text(
                    text = (message.value) ?: " ",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                Text(
                    text = "OMG! Api có vấn đề.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }


}

