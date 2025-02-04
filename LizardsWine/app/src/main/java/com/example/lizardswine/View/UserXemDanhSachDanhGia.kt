package com.example.lizardswine.View

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lizardswine.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DanhSachDanhGia(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Đánh giá",
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
                ),
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // First Review Section
            UserReview(
                avatarResId = R.drawable.vietnam,
                username = "sobdsk",
                reviewDate = "05/11/2023",
                rating = 5,
                reviewContent = "Hub của Ugreen rất yên tâm về chất lượng, vỏ bọc kim loại tản nhiệt tốt, dây cáp màu đen bọc vải dù, dùng lâu chỉ hơi ấm, các cổng kết nối chắc chắn và hoạt động tốt, shop giao hàng nhanh chưa đến 20h đã nhận được hub, giá rất cạnh tranh!",
                images = listOf(R.drawable.vietnam, R.drawable.vietnam, R.drawable.vietnam)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Second Review Section
            UserReview(
                avatarResId = R.drawable.vietnam,
                username = "daicuongduong",
                reviewDate = "04/11/2023",
                rating = 5,
                reviewContent = "Giao hàng hoả tốc trong đêm, quá tuyệt vời. Hàng đẹp, nguyên seal và hoạt động rất tốt!",
                images = listOf(R.drawable.vietnam, R.drawable.vietnam)
            )
        }
    }
}

@Composable
fun UserReview(
    avatarResId: Int,
    username: String,
    reviewDate: String,
    rating: Int,
    reviewContent: String,
    images: List<Int>
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = avatarResId),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = username,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                )
                Text(
                    text = reviewDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            repeat(rating) {
                Icon(
                    painter = painterResource(id = android.R.drawable.btn_star_big_on),
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = reviewContent,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        ImageGrid(images = images)
    }
}

@Composable
fun ImageGrid(images: List<Int>) {
    Column {
        val rows = images.chunked(2)
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { imageResId ->
                    ProductImage(imageResId = imageResId)
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ProductImage(imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}
