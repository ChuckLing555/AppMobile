package com.example.lizardswine.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.ViewModel.DangKyViewModel
import com.example.lizardswine.ViewModel.DangNhapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DangKy(navHostController: NavHostController, dangKyViewModel: DangKyViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birth by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val xuatloi = dangKyViewModel.registerState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            navHostController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Text(
                            text = "Đăng ký",
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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
                .background(color = Color(0xFFE8F5E9))
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { Text("Tên đăng nhập", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = username,
                    onValueChange = { if (it.length <= 20) username = it },
                    placeholder = { Text("Tên đăng nhập (tối đa 20 ký tự)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { Text("Mật khẩu", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Ít nhất 8 ký tự, chữ số và ký tự") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { Text("Nhập lại mật khẩu", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = { Text("Nhập lại mật khẩu") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { Text("Họ và tên", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = fullname,
                    onValueChange = { fullname = it },
                    placeholder = { Text("Họ và tên của bạn") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { Text("Số điện thoại", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = { Text("Số điện thoại của bạn") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { Text("Email", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email liên hệ") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { Text("Ngày sinh", fontSize = 14.sp, color = Color(0xFF188158), fontWeight = FontWeight.Bold) }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                OutlinedTextField(
                    value = birth,
                    onValueChange = { birth = it },
                    placeholder = { Text("Ngày sinh (YYYY-MM-DD)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
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
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                Button(
                    onClick = {
                        try {
                            errorMessage = validateInputss(username, fullname, password, confirmPassword, email, phone, birth)
                            if (errorMessage.isEmpty()) {
                                if(!xuatloi.value.toString().contains("thành công"))
                                { errorMessage = "Người dùng đã tồn tại" }
                                else{
                                    dangKyViewModel.dangKy(username, fullname, password, email, phone, birth)
                                    navHostController.navigate(Screen.DangNhap.route)
                                }
                            }else {
                                errorMessage = validateInputss(username, fullname, password, confirmPassword, email, phone, birth)
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF188158))
                ) {
                    Text("Đăng ký", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}
fun validateInputss(
    username: String,
    fullname: String,
    password: String,
    confirmPassword: String,
    email: String,
    phone: String,
    birth: String
): String {
    val kiemTraEmail = Regex("^[A-Za-z0-9+_.-]+@(.+\\.com)$")
    val kiemTraSDT = Regex("^\\d{10,11}$")
    val kiemTraNgayThangNam = Regex("^\\d{4}-\\d{2}-\\d{2}$")
    val kiemTraMatKhau = Regex("^(?=.*[!@#\$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#\$%^&*(),.?\":{}|<>]{8,}$")

    return when {
        username.isBlank() -> "Tên đăng nhập không được để trống"
        password.isBlank() -> "Mật khẩu không được để trống"
        !password.matches(kiemTraMatKhau) -> "Mật khẩu phải ít nhất 8 ký tự và chứa ít nhất 1 ký tự đặc biệt"
        password != confirmPassword -> "Mật khẩu và nhập lại mật khẩu không khớp"
        fullname.isBlank() -> "Họ và tên không được để trống"
        !phone.matches(kiemTraSDT) -> "Số điện thoại không hợp lệ"
        email.isBlank() -> "Email không được để trống"
        !email.matches(kiemTraEmail) -> "Email không hợp lệ"
        birth.isBlank() -> "Ngày sinh không được để trống"
        !birth.matches(kiemTraNgayThangNam) -> "Ngày sinh phải có định dạng YYYY-MM-DD"
        else -> ""
    }
}

