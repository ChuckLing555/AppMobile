package com.example.lizardswine.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lizardswine.Model.Anh
import com.example.lizardswine.Model.ChiTietHoaDon
import com.example.lizardswine.Model.DS_AnhRuou
import com.example.lizardswine.R
import com.example.lizardswine.View.Custom_Compose.CardDiaChiNguoiDung
import com.example.lizardswine.View.Custom_Compose.CardThongTinRuou

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThanhToan(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Thanh toán",
                        fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White
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
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF0A2E1F))
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp).navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column ()
                {
                    Text(text = "Tổng tiền:", color = Color.Black, fontSize = 13.sp)
                    Text(text = "850.000đ", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF004D40))
                ) {
                    Text(text = "Đặt hàng", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            ChiTietThanhToan()
        }
    }
}

@Composable
fun ChiTietThanhToan() {
    val ruou = ChiTietHoaDon(1, listOf<DS_AnhRuou>(
        DS_AnhRuou(1, "https://bewinemart.ducanhzed.com/uploads/images/medium_images/Vang-Femar-Roma-rosso-DOC.jpg")
    ), "Vang", 2, 150000, 350000)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        //CardDiaChiNguoiDung()

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp) ,
            backgroundColor = Color(0xFFE8F5E9),
        ) {
            Column (modifier = Modifier.width(intrinsicSize = IntrinsicSize.Min).padding(10.dp))
            {
                Column{
//                    hoaDonData?.ChiTietHoaDon?.forEach{
                            //ruou ->
                        CardThongTinRuou (ruou)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 6.dp).padding(8.dp)
                )

                Row (modifier = Modifier.fillMaxWidth().padding(10.dp), Arrangement.End)
                {
                    Text(
                        text = "Thành tiền: đ",//${hoaDonData?.TongTien}
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            backgroundColor = Color(0xFFE8F5E9),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth())
            {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Phương thức thanh toán",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Xem tất cả >>",
                        color = Color(0xFF1B5E20),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable { }
                            .padding(8.dp)
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.momo_ic),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(70.dp).padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Ví MoMo",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFFE8F5E9),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tổng tiền hàng")
                    Text(text = "880.000đ")
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Phí vận chuyển")
                    Text(text = "20.000đ")
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Giảm giá dành cho bạn", color = Color.Red)
                    Text(text = "-50.000đ", color = Color.Red)
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tổng tiền sản phẩm", fontWeight = FontWeight.Bold)
                    Text(text = "850.000đ", fontWeight = FontWeight.Bold)
                }
            }
        }


    }


}

