package com.example.lizardswine.View.Custom_Compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
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
import com.example.lizardswine.View.ChoXacNhan
import com.example.lizardswine.View.DS_ChoXacNhan
import com.example.lizardswine.View.DS_DaDuyet
import com.example.lizardswine.View.DS_DaGiao
import com.example.lizardswine.View.DS_DaHuy
import com.example.lizardswine.View.DS_DangGiao
import com.example.lizardswine.View.DaGiao
import com.example.lizardswine.View.DaHuy
import com.example.lizardswine.View.DaXacNhan
import com.example.lizardswine.View.DangGiao
import com.example.lizardswine.ViewModel.HoaDonViewModel


@Composable
fun TabLayout(navHostController: NavHostController) {

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Chờ xác nhận", "Đã xác nhận", "Đang giao", "Đã giao", "Đã hủy")

    Column {
        ScrollableTabRow(
            backgroundColor = Color(0xFF009688),
            selectedTabIndex = selectedTabIndex,
            edgePadding = 5.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xFF0A2E1F)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            title,
                            color = if (selectedTabIndex == index) Color(0xFF0A2E1F) else Color.White,
                            style = MaterialTheme.typography.body2, fontSize = 16.sp, fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> DS_ChoXacNhan(navHostController)
            1 -> DS_DaDuyet(navHostController)
            2 -> DS_DangGiao(navHostController)
            3 -> DS_DaGiao(navHostController)
            4 -> DS_DaHuy(navHostController)
        }
    }
}


@Composable
fun TabLayoutUser(navHostController: NavHostController, MaND: Int) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Chờ xác nhận", "Đã xác nhận", "Đang giao", "Đã giao", "Đã hủy")

    Column {
        ScrollableTabRow(
            backgroundColor = Color(0xFF009688),
            selectedTabIndex = selectedTabIndex,
            edgePadding = 5.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xFF0A2E1F)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            title,
                            color = if (selectedTabIndex == index) Color(0xFF0A2E1F) else Color.White,
                            style = MaterialTheme.typography.body2,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ChoXacNhan(navHostController, MaND)
            1 -> DaXacNhan(navHostController, MaND)
            2 -> DangGiao(navHostController, MaND)
            3 -> DaGiao(navHostController, MaND)
            4 -> DaHuy(navHostController, MaND)
        }
    }
}