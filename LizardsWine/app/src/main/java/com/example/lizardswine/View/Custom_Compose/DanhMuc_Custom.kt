package com.example.lizardswine.View.Custom_Compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Model.LoaiRuou
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.ViewModel.RuouViewModel

@Composable
fun DanhMucRuou(navHostController: NavHostController, viewModel: RuouViewModel = viewModel()) {

    val danhMucRuou = viewModel.loairuouList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.layDanhMucRuou()
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when {
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF0A2E1F)//
                )
            }

            danhMucRuou.value.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    items(danhMucRuou.value.size) { index ->
                        val danhmuc = danhMucRuou.value[index]
                        ChiTietDanhMucRuou(danhmuc, navHostController)
                    }
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

@Composable
fun ChiTietDanhMucRuou(danhMuc: LoaiRuou, navHostController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { if (danhMuc.DanhMucCon.isNotEmpty()) isExpanded = !isExpanded }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = danhMuc.TenLoaiRuou,
                fontSize = 20.sp,
                color = Color(0xFF188158)
            )
            if (danhMuc.DanhMucCon.isNotEmpty()) {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                danhMuc.DanhMucCon.forEach { subItem ->
                Text(
                    text = subItem.TenLoaiRuou,
                    fontSize = 16.sp,
                    color = Color(0xFF188158),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navHostController.navigate(Screen.DSRuouTheoDanhMuc.route + "?MaLoaiR=${subItem.MaLoaiR}&OtherParam=${subItem.TenLoaiRuou}")
                        }
                        .padding(8.dp)
                )
            }
        }
        }
    }
}

