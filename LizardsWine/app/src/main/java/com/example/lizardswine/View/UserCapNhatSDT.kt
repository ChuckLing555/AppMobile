package com.example.lizardswine.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuaSoDienThoai(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sửa số điện thoại",
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(8.dp),
        ) {
            // Biến lưu trạng thái và thông báo lỗi
            var phoneNumber by remember { mutableStateOf("") }
            var errorMessage by remember { mutableStateOf("") }

            // TextField: Nhập số điện thoại
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { input ->
                    phoneNumber = input
                    errorMessage = if (isValidVietnamesePhoneNumber(input)) {
                        ""
                    } else {
                        "Số điện thoại không hợp lệ. Phải có 10 chữ số và bắt đầu bằng 0."
                    }
                },
                label = { Text("Nhập số điện thoại") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE8F5E9),
                        shape = RoundedCornerShape(8.dp)
                    ),
                singleLine = true,
                trailingIcon = {
                    if (phoneNumber.isNotEmpty()) {
                        IconButton(onClick = { phoneNumber = "" }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF003D24), // Màu viền khi focus
                    unfocusedBorderColor = Color.Gray, // Màu viền khi không focus
                    containerColor = Color.Transparent // Nền trong suốt
                )
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Đẩy nút xuống cuối màn hình

            Button(
                onClick = {
                    if (errorMessage.isEmpty()) {
                        // Xử lý lưu số điện thoại
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF003D24), // Màu xanh đậm
                    contentColor = Color.White // Màu chữ trắng
                ),
                shape = RoundedCornerShape(8.dp) // Bo góc
            ) {
                Text(
                    text = "Lưu",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

fun isValidVietnamesePhoneNumber(phone: String): Boolean {
    val regex = Regex("^0[0-9]{9}\$") // Bắt đầu bằng 0 và có 10 chữ số
    return regex.matches(phone)
}