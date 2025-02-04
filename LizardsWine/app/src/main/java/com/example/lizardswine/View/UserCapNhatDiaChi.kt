package com.example.lizardswine.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Model.DiaChiRequest
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.ViewModel.LayThongTinNguoiDung
import com.example.lizardswine.ViewModel.ThemDiaChi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapNhatDiaChi(navHostController: NavHostController, MaND : Int, viewModel: ThemDiaChi = viewModel(),) {
    var soNha by remember { mutableStateOf("") }
    var tenDuong by remember { mutableStateOf("") }
    var tenXa by remember { mutableStateOf("") }
    var tenHuyen by remember { mutableStateOf("") }
    var tenTinh by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxWidth().navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            navHostController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Close",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Text(
                            text = "Thêm địa chỉ",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2E1F),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                Button(
                    onClick = {
                        try {
                            errorMessage = validateAddressInputs(soNha, tenDuong, tenXa, tenHuyen, tenTinh)
                            if (errorMessage.isEmpty()) {
                                viewModel.themDiaChiND(MaND, soNha, tenDuong, tenXa, tenHuyen, tenTinh)
                                navHostController.navigate(Screen.DSDiaChi.route + "?MaND=${MaND}")
                            }
                        } catch (e: Exception) {
                            errorMessage = "Đã xảy ra lỗi: ${e.message}"
                            Log.e("DangKyError", "Lỗi đăng ký: ${e.message}")
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF188158)
                    ),
                ) {
                    Text(
                        "Lưu địa chỉ",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                Text(
                    "Số nhà",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = soNha,
                    onValueChange = { soNha = it },
                    placeholder = { Text("vd: 1027/ 26/ 17") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    "Tên đường",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = tenDuong,
                    onValueChange = { tenDuong = it },
                    placeholder = { Text("vd: Đường B7") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    "Phường/ Xã/ Thị Trấn",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = tenXa,
                    onValueChange = { tenXa = it },
                    placeholder = { Text("vd: Phước Kiển") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    "Huyện/Phường/Xã",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = tenHuyen,
                    onValueChange = { tenHuyen = it },
                    placeholder = { Text("vd: Nhà Bè") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                Text(
                    "Tỉnh/ Thành Phố",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = tenTinh,
                    onValueChange = { tenTinh = it },
                    placeholder = { Text("vd: Hồ Chí Minh") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

fun validateAddressInputs(
    soNha: String,
    tenDuong: String,
    tenXa: String,
    tenHuyen: String,
    tenTinh: String
): String {

    return when {
        soNha.isBlank() -> "Số nhà không được để trống"
        tenDuong.isBlank() -> "Tên đường không được để trống"
        tenXa.isBlank() -> "Tên xã không được bỏ trống"
        tenHuyen.isBlank() -> "Tên huyện không được bỏ trống"
        tenTinh.isBlank() -> "Tên tỉnh không được để trống"
        else -> ""
    }
}
