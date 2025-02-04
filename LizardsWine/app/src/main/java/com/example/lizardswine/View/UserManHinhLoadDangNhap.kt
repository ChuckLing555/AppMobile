package com.example.lizardswine.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Data.User
import com.example.lizardswine.Data.UserStore
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.ViewModel.DangNhapViewModel

@Composable
fun Loading(navHostController: NavHostController, username: String, password: String, viewModel: DangNhapViewModel = viewModel()){
    val nguoiDung by viewModel.loginState.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.error.collectAsState()
    val nguoiDungData = nguoiDung?.getOrNull()

    val context = LocalContext.current
    val store = UserStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    val isNavigated = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.dangNhap(username, password)
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Transparent) ) {
        when {
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0A2E1F)
                )
            }

            nguoiDung != null -> {
                LaunchedEffect(nguoiDung) {
                    store.saveUserInfor(
                        User(
                            userID = nguoiDungData?.MaND!!,
                            user_token = nguoiDungData.MaND.toString()
                        )
                    )

                    // Điều hướng khi token đã sẵn sàng
                    if (tokenText.value.isNotEmpty()) {
                        isNavigated.value = true // Đảm bảo chỉ điều hướng một lần
                        navHostController.navigate(Screen.ManHinhChinh.route + "?MaND=${nguoiDungData.MaND}")
                    }
                }
            }

            message.value != null -> {
                LaunchedEffect(Unit) {
                    navHostController.navigate(Screen.DangNhap.route + "?message=${message.value}")
                }
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