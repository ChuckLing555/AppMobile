package com.example.lizardswine.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Navigation.NavItem
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.View.Custom_Compose.CardHoaDon
import com.example.lizardswine.View.Custom_Compose.CardHoaDonND
import com.example.lizardswine.View.Custom_Compose.TabLayoutUser
import com.example.lizardswine.ViewModel.HoaDonViewModel
import com.example.lizardswine.ViewModel.LayHoaDonNguoiDung


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonDaMua(navHostController: NavHostController, MaND: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Đơn hàng của bạn",
                        color = Color.White,
                        fontSize = 20.sp,
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
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF004D40) // Màu nền xanh
                )
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TabLayoutUser(navHostController, MaND)
        }
    }
}
@Composable
fun ChoXacNhan(navHostController: NavHostController, MaND: Int, viewModel: LayHoaDonNguoiDung = remember { LayHoaDonNguoiDung() }){

    LaunchedEffect(Unit) {
        viewModel.dsHoaDonND(MaND, 1)
    }

    val hoaDonListND = viewModel.hoaDonNDList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when{
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0A2E1F)
                )
            }
            hoaDonListND.value.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    items(hoaDonListND.value.size) { index ->
                        val hoadon = hoaDonListND.value[index]
                        CardHoaDonND(
                            hoadonND = hoadon,
                            1,
                            onClickCard = {
                                navHostController.navigate(Screen.ChiTietDonND.route + "?MaHD=${hoadon.MaHD}")
                            }
                        )

                    }
                }
            }
            message.value != null -> {
                androidx.compose.material.Text(
                    text = (message.value + " Chờ xác nhận.") ?: " ",
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
@Composable
fun DaXacNhan(navHostController: NavHostController, MaND: Int, viewModel: LayHoaDonNguoiDung = viewModel()){
    val hoaDonList = viewModel.hoaDonNDList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.dsHoaDonND(MaND, 2)
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
                        CardHoaDonND(
                            hoadonND = hoadon,
                            2,
                            onClickCard = {
                                navHostController.navigate(Screen.ChiTietDonND.route + "?MaHD=${hoadon.MaHD}")
                            }
                        )

                    }
                }
            }
            message.value != null -> {
                androidx.compose.material.Text(
                    text = (message.value + " Đã duyệt.") ?: " ",
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
@Composable
fun DangGiao(navHostController: NavHostController, MaND: Int, viewModel: LayHoaDonNguoiDung = viewModel()){
    val hoaDonList = viewModel.hoaDonNDList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.dsHoaDonND(MaND, 3)
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
                        CardHoaDonND(
                            hoadonND = hoadon,
                            3,
                            onClickCard = {
                                navHostController.navigate(Screen.ChiTietDonND.route + "?MaHD=${hoadon.MaHD}")
                            }
                        )

                    }
                }
            }
            message.value != null -> {
                androidx.compose.material.Text(
                    text = (message.value + " Đang giao.") ?: " ",
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
@Composable
fun DaGiao(navHostController: NavHostController, MaND: Int, viewModel: LayHoaDonNguoiDung = viewModel()){
    val hoaDonList = viewModel.hoaDonNDList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.dsHoaDonND(MaND, 4)
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
                        CardHoaDonND(
                            hoadonND = hoadon,
                            4,
                            onClickCard = {
                                navHostController.navigate(Screen.ChiTietDonND.route + "?MaHD=${hoadon.MaHD}")
                            }
                        )

                    }
                }
            }
            message.value != null -> {
                androidx.compose.material.Text(
                    text = (message.value + " Đã giao.") ?: " ",
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
@Composable
fun DaHuy(navHostController: NavHostController, MaND: Int, viewModel: LayHoaDonNguoiDung = viewModel()){
    val hoaDonList = viewModel.hoaDonNDList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.dsHoaDonND(MaND, 5)
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
                        CardHoaDonND(
                            hoadonND = hoadon,
                            5,
                            onClickCard = {
                                navHostController.navigate(Screen.ChiTietDonND.route + "?MaHD=${hoadon.MaHD}")
                            }
                        )

                    }
                }
            }
            message.value != null -> {
                androidx.compose.material.Text(
                    text = (message.value + " Đã giao.") ?: " ",
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