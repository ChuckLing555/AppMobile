package com.example.lizardswine.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lizardswine.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DanhGiaSanPham(navHostController: NavHostController) {
    val rating = remember { mutableStateOf(4) }
    val feedbackText = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Đánh giá sản phẩm",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2E1F) // Màu xanh đậm
                ),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vang), // Thay bằng ảnh thực tế
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Ballantine's Reserve")
                    Text(text = "Việt Nam", color = Color.Gray)
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Chất lượng sản phẩm", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Row{

                }
                for (i in 1..5) {
                    val alpha = if (i <= rating.value) 1f else 0.5f
                    Icon(
                        imageVector = if (i <= rating.value) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Star $i",
                        tint = Color(0xFFFFD700), // Màu vàng
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { rating.value = i }
                            .alpha(alpha)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                val ratingMessage = when (rating.value) {
                    5 -> "Rất hài lòng"
                    4 -> "Hài lòng"
                    3 -> "Bình thường"
                    2 -> "Không hài lòng"
                    1 -> "Rất không hài lòng"
                    else -> ""
                }
                Text(
                    text = ratingMessage,
                    color = Color(0xFF388E3C),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
            Text(text = "Nội dung đánh giá sản phẩm", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            TextField(
                value = feedbackText.value,
                onValueChange = { feedbackText.value = it },
                placeholder = { Text(text = "Nhập nội dung ở đây...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp),
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = { /* Thêm hình ảnh */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                    ,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F5E9)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_camera),
                        contentDescription = "Thêm ảnh",
                        tint = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Thêm Ảnh", color = Color.Black)
                }
                Button(
                    onClick = { /* Thêm video */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F5E9)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_upload_you_tube),
                        contentDescription = "Thêm video",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Thêm Video", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nút gửi phản hồi
            Button(
                onClick = { /* Gửi phản hồi */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004D40)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Gửi phản hồi",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
