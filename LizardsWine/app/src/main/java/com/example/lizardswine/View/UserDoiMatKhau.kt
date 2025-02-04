package com.example.lizardswine.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoiMatKhau(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Đổi mật khẩu",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF003D24) // Màu nền TopAppBar
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TextField: Mật khẩu cũ
            var oldPassword by remember { mutableStateOf("") }
            PasswordTextField(
                value = oldPassword,
                onValueChange = { oldPassword = it },
                label = "Nhập mật khẩu cũ"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TextField: Mật khẩu mới
            var newPassword by remember { mutableStateOf("") }
            PasswordTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = "Nhập mật khẩu mới"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TextField: Nhập lại mật khẩu mới
            var confirmPassword by remember { mutableStateOf("") }
            PasswordTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Nhập lại mật khẩu mới"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Text: Hiển thị lỗi
            var errorMessage by remember { mutableStateOf("") }
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Button: Lưu mật khẩu
            Button(
                onClick = {
                    // Kiểm tra điều kiện mật khẩu
                    when {
                        newPassword.length < 8 ||
                                !newPassword.any { it.isDigit() } ||
                                !newPassword.any { !it.isLetterOrDigit() } -> {
                            errorMessage = "Mật khẩu mới phải có ít nhất 8 ký tự, 1 số, và 1 ký tự đặc biệt."
                        }
                        newPassword == oldPassword -> {
                            errorMessage = "Mật khẩu mới không được trùng với mật khẩu cũ."
                        }
                        newPassword != confirmPassword -> {
                            errorMessage = "Nhập lại mật khẩu mới không khớp."
                        }
                        else -> {
                            errorMessage = ""
                            // Logic đổi mật khẩu thành công
                            // Hiển thị thông báo hoặc điều hướng
                        }
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
                    text = "Lưu mật khẩu",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFE8F5E9), // Màu nền xanh nhạt
                shape = RoundedCornerShape(8.dp) // Bo góc
            ),
        singleLine = true,
        visualTransformation = if (value.isNotEmpty()) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF003D24), // Màu viền khi focus
            unfocusedBorderColor = Color.Gray, // Màu viền khi không focus
            containerColor = Color.Transparent // Nền trong suốt
        )
    )
}
