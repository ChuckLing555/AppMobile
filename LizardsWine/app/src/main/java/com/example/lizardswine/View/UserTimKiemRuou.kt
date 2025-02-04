package com.example.lizardswine.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Model.Ruou
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.R
import com.example.lizardswine.View.Custom_Compose.CardRuou
import com.example.lizardswine.ViewModel.RuouViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhTimKiem (navHostController: NavHostController, viewModel: RuouViewModel = viewModel()) {

    var inputText by remember { mutableStateOf("")}
    var trangThai by remember { mutableStateOf(false) }
    val quanLiTrangThai = remember { FocusRequester() }


    val dsRuou by if (inputText.isEmpty()) {
        viewModel.getEmptyList().collectAsState()
    } else {
        viewModel.timKiemRuou(inputText).collectAsState()
    }
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        BasicTextField(
                            value = inputText,
                            onValueChange = {
                                inputText = it },
                            modifier = Modifier
                                .height(40.dp)
                                .background(Color.White, shape = RoundedCornerShape(5.dp))
                                .focusRequester(quanLiTrangThai)
                                .onFocusChanged { trangThai = it.isFocused },
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    IconButton(
                                        onClick = {
                                            navHostController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = null,
                                            tint = Color(0xFF004D40)
                                        )
                                    }

                                    if (!trangThai && inputText.isEmpty()) {
                                        Text(
                                            text = "Nhập nội dung tìm kiếm...",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                    innerTextField()

                                }
                            },
                            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004D40),
                    titleContentColor = Color.White
                ),

            )
        }
    ) {  innerPadding ->
        Box (modifier = Modifier.padding(8.dp)){
        when{
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0A2E1F)
                )
            }
            dsRuou.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Hiển thị 2 cột
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    items(dsRuou) { ruou ->
                        CardRuou(ruou) {
                            navHostController.navigate(Screen.ChiTietRuou.route + "?MaR=${ruou.MaR}")
                        }
                    }
                }
            }
            message.value != null -> {
                androidx.compose.material.Text(
                    text = (message.value) ?: "Opp! Có vẻ như chúng tôi đã hết rượu cho danh mục này!",
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

