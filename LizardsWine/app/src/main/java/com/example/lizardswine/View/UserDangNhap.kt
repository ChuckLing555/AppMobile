package com.example.lizardswine.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import java.util.regex.Pattern
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.lizardswine.Api.NguoiDungApiResponse
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.ViewModel.DangNhapViewModel
import com.example.lizardswine.ViewModel.HoaDonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DangNhap(navHostController: NavHostController, message: String = "") {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect (Unit){
        username = ""
        password = ""
    }
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
                            text = "Đăng nhập",
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFE8F5E9))
                    .padding(12.dp),
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Tên đăng nhập",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        if (it.length <= 20) {
                            username = it
                        }
                    },
                    placeholder = { Text("Tên đăng nhập (tối đa 20 ký tự)", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Mật khẩu",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Mật khẩu (ít nhất 8 ký tự)", color = Color.Gray) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF188158),
                        unfocusedBorderColor = Color.Gray,
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    if(message != "") message else errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val validationMessage = validateInputss(username, password)
                        if (validationMessage.isEmpty()) {
                            if(username != "" && password != "")
                                navHostController.navigate(Screen.Loading.route + "?TenDangNhap=${username}&MatKhau=${password}")
                        }
                        else{
                            errorMessage = validationMessage
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = buttonColors(backgroundColor = Color(0xFF188158)),
                ) {
                    Text(
                        "Đăng nhập",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        "Quên mật khẩu",
                        color = Color(0xFF188158),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable { }
                    )
                }
            }

    }
}

fun validateInputss(username: String, password: String): String {
    if (username.isEmpty()) {
        return "Tên đăng nhập không được để trống."
    }
    if (password.isEmpty()) {
        return "Mật khẩu không được để trống."
    }
    if (username.length > 20) {
        return "Tên đăng nhập tối đa 20 ký tự."
    }
//    if (!isPasswordValid(password)) {
//        return "Mật khẩu phải có ít nhất 8 ký tự, bao gồm 1 số và 1 ký tự đặc biệt."
//    }
    return ""
}

fun isPasswordValid(password: String): Boolean {
    val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,}$")
    return passwordPattern.matcher(password).matches()
}
