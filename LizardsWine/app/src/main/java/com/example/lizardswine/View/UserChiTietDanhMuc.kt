package com.example.lizardswine.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lizardswine.Model.Anh
import com.example.lizardswine.Model.Ruou
import com.example.lizardswine.Navigation.NavItem
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.R
import com.example.lizardswine.View.Custom_Compose.CardDSRuouTheoDanhMuc
import com.example.lizardswine.View.Custom_Compose.CardHoaDon
import com.example.lizardswine.View.Custom_Compose.CardRuou
import com.example.lizardswine.ViewModel.RuouViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun XemChiTietDanhMucSP(navHostController: NavHostController,maLoaiR: Int, tenDanhMuc: String ,viewModel: RuouViewModel = viewModel() ){
    val dsRuouTheoMa = viewModel.ruouTheoMaList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val message = viewModel.message.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.layDSRuouTheoMaLoaiR(maLoaiR)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = tenDanhMuc,
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF004D40)
                )
            )
        }

    ) { innerPadding ->
        Box (modifier = Modifier.padding(8.dp)){
            when{
                isLoading.value -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFF0A2E1F)
                    )
                }
                dsRuouTheoMa.value.isNotEmpty() -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Hiển thị 2 cột
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(dsRuouTheoMa.value) { ruou ->
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
