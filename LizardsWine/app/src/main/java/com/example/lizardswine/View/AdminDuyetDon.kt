package com.example.lizardswine.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Navigation.NavItem
import com.example.lizardswine.View.Custom_Compose.CardHoaDon
import com.example.lizardswine.View.Custom_Compose.TabLayout
import com.example.lizardswine.ViewModel.HoaDonViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Admin_ManHinhChinh(navHostController: NavHostController, viewModel: HoaDonViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(top = 20.dp, end = 10.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Quản lí đơn hàng",
                        color = Color.White,
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF009688)
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            TabLayout(navHostController)
        }
    }
}



@Composable
fun DS_ChoXacNhan(navHostController: NavHostController, viewModel: HoaDonViewModel = viewModel()) {

    val hoaDonList = viewModel.hoaDonList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.layDSHoaDonTheoTrangThai(1)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when{
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0A2E1F)
                )
            }
            hoaDonList.value.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    items(hoaDonList.value.size) { index ->
                        val hoadon = hoaDonList.value[index]
                        CardHoaDon(
                            navHostController,
                            hoadon = hoadon,
                            1,
                            onClickCard = {
                                navHostController.navigate(NavItem.AdminChiTietDonHang.route + "?MaHD=${hoadon.MaHD}")
                            },
                            viewModel
                        )

                    }
                }
            }
            message.value != null -> {
                Text(
                    text = (message.value + " Chờ xác nhận.") ?: " ",
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
