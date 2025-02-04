package com.example.lizardswine.View

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.Navigation.UserNavGraph
import com.example.lizardswine.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeToLizardWine(navHostController: NavHostController) {
    var trangThai by remember { mutableStateOf(false) }
    val ruouTrai by animateDpAsState(
        targetValue = if (trangThai) 0.dp else (-500).dp, //Đơn vị từ 0 -1000
        animationSpec = tween(durationMillis = 2500) //Thời gian xuất hiện
    )
    val ruouPhai by animateDpAsState(
        targetValue = if (trangThai) 0.dp else 500.dp,
        animationSpec = tween(durationMillis = 2500)
    )
    LaunchedEffect(Unit) {
        delay(100) //Thời gian delay xuất hiện
        trangThai = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(horizontal = 8.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceEvenly,
    )
    {
        Column {  }
        Column {  }

        ProductRowWithImageOnLeft(
            imageRes = R.drawable.wine1,
            country = "Ý",
            name = "Vang F Gold Limited",
            price = "888,000 VND",
            type = "Thuế bán hàng",
            volume = "Dung tích: 750ml",
            year = "2020",
            modifier = Modifier.offset(x = ruouTrai)
        )
        ProductRowWithImageOnRight(
            imageRes = R.drawable.ruou2tbn,
            country = "Ý",
            name = "Vang Collefr Selezione",
            price = "999,000 VND",
            type = "Thuế bán hàng",
            volume = "Dung tích: 750ml",
            year = "2020",
            modifier = Modifier.offset(x = ruouPhai)
        )


        AnimatedVisibility(
            visible = trangThai,
            enter = fadeIn(tween(durationMillis = 3000))
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Trải nghiệm hoàn hảo",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF188158)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Chúng tôi mang đến cho bạn những chai rượu vang thương hạng, "
                            + "đa dạng và các loại đồ uống tinh túy từ khắp nơi trên thế giới. "
                            + "Cam kết mức giá tốt nhất kèm theo thông tin minh bạch, giúp bạn an tâm lựa chọn và thưởng thức.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // man hinh dang nhap
                            navHostController.navigate(Screen.DangNhap.route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        colors = buttonColors(backgroundColor = Color(0xFF188158)),
                        border = BorderStroke(1.dp, Color(0xFF188158)),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(text = "Đăng nhập",color = Color.White, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            // man hinh dang ky
                            navHostController.navigate(Screen.DangKy.route)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        colors = buttonColors(backgroundColor = Color(0xFFE8F5E9)),
                        border = BorderStroke(1.dp, Color(0xFF188158)),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(text = "Đăng ký", fontSize = 16.sp, color = Color(0xFF188158))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Tiếp tục với tài khoản khách",
                    fontSize = 14.sp,
                    color = Color(0xFF188158),
                    modifier = Modifier.align(Alignment.CenterHorizontally).clickable(onClick = {})
                )
            }
        }
    }
}

@Composable
fun ProductRowWithImageOnLeft(
    imageRes: Int,
    country: String,
    name: String,
    price: String,
    type: String,
    volume: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = name,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(12.dp)
        ) {
            // Product details inside the column
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quốc gia: $country",
                    fontSize = 12.sp,
                    color = Color(0xFF0A2E1F),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$price VND",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.italy),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF188158)
                    )
                }
                Text(
                    text = "Thuế bán",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "$volume",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = "$year",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(top = 4.dp, start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ProductRowWithImageOnRight(
    imageRes: Int,
    country: String,
    name: String,
    price: String,
    type: String,
    volume: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent)
                .padding(12.dp)
        ) {
            // Product details inside the column
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quốc gia: $country",
                    fontSize = 12.sp,
                    color = Color(0xFF0A2E1F),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$price VND",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Image(
                    painter = painterResource(id = R.drawable.italy),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF188158)
                )

            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Thuế bán",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "$volume",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(top = 4.dp, end = 4.dp)
                )
                Text(
                    text = "$year",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }

        Image(
            painter = painterResource(imageRes),
            contentDescription = name,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}