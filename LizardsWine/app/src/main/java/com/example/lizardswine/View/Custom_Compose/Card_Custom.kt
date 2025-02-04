package com.example.lizardswine.View.Custom_Compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.lizardswine.Model.ChiTietHoaDon
import com.example.lizardswine.Model.DiaChi
import com.example.lizardswine.Model.HoaDon
import com.example.lizardswine.Model.Ruou
import com.example.lizardswine.Navigation.Screen
import com.example.lizardswine.R
import com.example.lizardswine.ViewModel.HoaDonViewModel
import com.example.lizardswine.ViewModel.LayHoaDonNguoiDung
import com.example.lizardswine.ViewModel.LayThongTinNguoiDung
import java.text.DecimalFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardHoaDon(navHostController: NavHostController, hoadon: HoaDon, manhinh: Int, onClickCard: () -> Unit, viewModel: HoaDonViewModel = viewModel()){
    //1 -> duyệt
    //2 -> đã duyệt
    //3 -> đang giao
    //4 -> đã giao
    //5 -> đã hủy
    val updateResultState = viewModel.updateResult.collectAsState()

    val formatter = DecimalFormat("#,###")

    Card(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE8F5E9),
        elevation = 2.dp,
        onClick = onClickCard
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            CardThongTinRuou(hoadon.ChiTietHoaDon[0])

            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                text = "Tổng số tiền (" + hoadon.SoLuongRuou + " sản phẩm): " + hoadon.TongThanhToan.let { formatter.format(it.toDouble()) }  + " VNĐ",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F7D34)
                ),
                textAlign = TextAlign.Right
            )

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                when(manhinh){
                    0 -> { }
                    1 -> {
                        ButtonAccept_Cancel(
                            onClickButton = {
                                viewModel.capNhatTrangThaiHuyHoaDon(maHD = hoadon.MaHD, 1)
                            },
                            colorText_Border = 0xFFFF5252,
                            text = "Hủy đơn hàng"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        ButtonAccept_Cancel(
                            onClickButton = {
                                //viewModel.capNhatTrangThaiHoaDon(hoaDon = hoaDonRequest)
                                viewModel.capNhatTrangThaiHoaDon(maHD = hoadon.MaHD, 1)
                            },
                            colorText_Border = 0xFF188158,
                            text = "Xác nhận đơn hàng"
                        )
                    }
                    2 -> {
                        ButtonAccept_Cancel(
                            onClickButton = {
                                viewModel.capNhatTrangThaiHuyHoaDon(maHD = hoadon.MaHD, 2)
                            },
                            colorText_Border = 0xFFFF5252,
                            text = "Hủy đơn hàng"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        ButtonAccept_Cancel(
                            onClickButton = {
                                //viewModel.capNhatTrangThaiHoaDon(hoaDon = hoaDonRequest)
                                viewModel.capNhatTrangThaiHoaDon(maHD = hoadon.MaHD, 2)
                            },
                            colorText_Border = 0xFF188158,
                            text = "Giao hàng"
                        )
                    }
                    3 -> {
                        ButtonAccept_Cancel(
                            onClickButton = {
                                viewModel.capNhatTrangThaiHoaDon(maHD = hoadon.MaHD, 3)
                            },
                            colorText_Border = 0xFF188158,
                            text = "Giao hàng thành công"
                        )
                    }
                    4 -> {
                        Text(
                            text = "Đơn hàng đã giao",
                            color = Color(0xFF188158)
                        )
                    }
                    5 -> {

                        Text(
                            text = "Lý do: ",
                            color = Color.Black
                        )
                        Text(
                            text = "Hết hàng",
                            color = Color(0xFF188158)
                        )
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardHoaDonND(hoadonND: HoaDon, manhinhND: Int, onClickCard: () -> Unit, viewModel: LayHoaDonNguoiDung = viewModel()){
    //1 -> duyệt
    //2 -> đã duyệt
    //3 -> đang giao
    //4 -> đã giao
    //5 -> đã hủy
    val formatter = DecimalFormat("#,###")

    Card(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE8F5E9),
        elevation = 2.dp,
        onClick = onClickCard
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            CardThongTinRuouND(hoadonND.ChiTietHoaDon[0])

            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                text = "Tổng số tiền (" + hoadonND.SoLuongRuou + " sản phẩm): " + hoadonND.TongThanhToan.let { formatter.format(it.toDouble()) }  + " VNĐ",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F7D34)
                ),
                textAlign = TextAlign.Right
            )

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                when(manhinhND){
                    0 -> { }
                    1, 2 -> {
                        ButtonAccept_Cancel(
                            onClickButton = {
                                viewModel.capNhatTrangThaiHuyHoaDonND(MaND = hoadonND.MaHD, 1)
                            },
                            colorText_Border = 0xFFFF5252,
                            text = "Hủy đơn hàng"
                        )

                    }
                    3 -> {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Đơn hàng đang giao",
                            color = Color(0xFF188158)
                        )
                    }
                    4 -> {
                        ButtonAccept_Cancel(
                            onClickButton = {
                            },
                            colorText_Border = 0xFF188158,
                            text = "Xác nhận 'Đã nhận được hàng'"
                        )

                    }
                    5 -> {

                        Text(
                            text = "Lý do: ",
                            color = Color.Black
                        )
                        Text(
                            text = "Hết tiền",
                            color = Color(0xFF188158)
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun CardThongTinRuou(ruou: ChiTietHoaDon) {

    val formatter = DecimalFormat("#,###")

    Row (
        modifier = Modifier.fillMaxWidth().height(100.dp)
    ){

        AsyncImage(
            model = ruou.DS_AnhRuou[0].AnhRuou,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )

        Column(
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = ruou.TenRuou,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A2E1F)
                )
            )
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "x " + ruou.SoLuong.toString(),
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    textAlign = TextAlign.Right
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${ruou.GiaBan.let { formatter.format(it.toDouble()) } } VNĐ" ?: "N/A",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0A2E1F)
                    ),
                    textAlign = TextAlign.Right
                )
            }

        }

    }
}

@Composable
fun CardThongTinRuouND(ruou: ChiTietHoaDon) {
    val formatter = DecimalFormat("#,###")

    Row (
        modifier = Modifier.fillMaxWidth().height(100.dp)
    ){

        AsyncImage(
            model = ruou.DS_AnhRuou[0].AnhRuou,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )

        Column(
            modifier = Modifier.fillMaxHeight().padding(start = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = ruou.TenRuou,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A2E1F)
                )
            )
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "x " + ruou.SoLuong.toString(),
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray),
                    textAlign = TextAlign.Right
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${ruou.GiaBan.let { formatter.format(it.toDouble()) } } VNĐ" ?: "N/A",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0A2E1F)
                    ),
                    textAlign = TextAlign.Right
                )
            }

        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardDiaChi(hoadon: Result<HoaDon>?, onClickDiaChi: () -> Unit){
    val hoaDonData = hoadon?.getOrNull()

    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(8.dp),
        onClick = onClickDiaChi
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "${hoaDonData?.TenDangNhap} | ${hoaDonData?.SoDienThoai}", fontWeight = FontWeight.Bold, color = Color(0xFF0A2E1F))
                Text(text = "${hoaDonData?.DCGiaoHang}")
            }
        }
    }
}

@Composable
fun CardPTThanhToan(hoadon: Result<HoaDon>?){
    val hoaDonData = hoadon?.getOrNull()

    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Thanh toán", fontWeight = FontWeight.Bold, color = Color(0xFF0A2E1F))

            Text(text = "${hoaDonData?.LoaiThanhToan}", fontWeight = FontWeight.Bold, color = Color(0xFF0A2E1F))

        }
    }
}

@Composable
fun CardChiTietThanhToan(hoadon: Result<HoaDon>?){

    val formatter = DecimalFormat("#,###")
    val hoaDonData = hoadon?.getOrNull()
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tổng tiền hàng", color = Color(0xFF0A2E1F))
                Text(text = hoaDonData?.TongTien?.let { formatter.format(it.toDouble()) }?: "N/A", color = Color(0xFF0A2E1F))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Phí vận chuyển", color = Color(0xFF0A2E1F))
                Text(text = "20.000đ", color = Color(0xFF0A2E1F))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Giảm giá dành cho bạn", color = Color(0xFF0A2E1F))
                Text(text = "${hoaDonData?.GiaTriKM} %", color = Color.Red)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tổng tiền sản phẩm", fontWeight = FontWeight.Bold, color = Color(0xFF0A2E1F))
                Text(text = hoaDonData?.TongThanhToan?.let { formatter.format(it.toDouble()) }?: "N/A", fontWeight = FontWeight.Bold, color = Color(0xFF0A2E1F))
            }
        }
    }
}

//--------------------------------------------------
@Composable
fun CardRuou(ruou: Ruou, onClickRuou: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val formatter = DecimalFormat("#,###")
    val formattedGiaBan = ruou.GiaBan?.let { formatter.format(it.toDouble()) } ?: "N/A"

    Card (
        modifier = Modifier.padding(8.dp).size(screenHeight * 7 / 20).clickable {onClickRuou()}, elevation = 2.dp
    ) {
        Column (
            modifier = Modifier.background(Color(0xFFE8F5E9)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = rememberAsyncImagePainter(ruou.AnhRuou[0].AnhRuou),
                contentDescription = null,
                modifier = Modifier
                    .size(screenWidth * 9 / 20)
                    .padding(15.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                text = ruou.TenRuou ?: "Không tên",
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 15.dp).fillMaxWidth()
            ) {
                Text(
                    text = "$formattedGiaBan VND",
                    fontSize = 14.sp,
                    color = Color(0xFF004D40)
                )
                Row{
                    Text(
                        text = "${ruou.DiemDG}",
                        fontSize = 12.sp,
                        color = Color(0xFF004D40)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                }

            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardDSRuouTheoDanhMuc(dsRuou: State<List<Ruou>>, navHostController: NavHostController){//

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Hiển thị 2 cột
            modifier = Modifier.height(700.dp)
        ) {
            items(dsRuou.value.size) { index ->
                val ruou = dsRuou.value[index]
                CardRuou(ruou) {
                    navHostController.navigate(Screen.ChiTietRuou.route  + "?MaR=${ruou.MaR}")
                }
            }
        }

}

@Composable
fun CardDanhGiaRuou(){

    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.anhuser),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Text(
                    text = "Chai đẹp", fontWeight = FontWeight.Bold,
                    color = Color(0xFF0A2E1F)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))

            androidx.compose.material3.Text(
                text = "Sản phẩm tốt, dùng rất ok, giao hàng cũng khá nhanh mọi người nên dùng thử nha :3",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(3) {
                    Image(
                        painter = painterResource(id = R.drawable.anhchairuou),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardDiaChiNguoiDung(diaChi: DiaChi, MaND: Int, viewModel: LayThongTinNguoiDung = viewModel()){
    LaunchedEffect (Unit){
        viewModel.layThongTin(MaND)
    }
    val thongtin by viewModel.thongTinTheoMa.collectAsState()
    val ThongTin = thongtin?.getOrNull()
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = ThongTin?.HoTen + " | " + ThongTin?.SoDienThoai, fontWeight = FontWeight.Bold)
                Text(text = diaChi.DCGiaoHang)
            }
        }

    }
}

@Composable
fun DSDiaChiNguoiDung(MaND : Int, viewModel: LayThongTinNguoiDung = viewModel()){
    LaunchedEffect (Unit){
        viewModel.layDiaChi(MaND)
    }
    val diachi by viewModel.diaChi.collectAsState()
    val DiaChi = diachi?.getOrNull()

    var chonDiaChi by remember { mutableStateOf("") }
    LaunchedEffect(DiaChi) {
        DiaChi?.find { it.IsDefault == 1 }?.let {
            chonDiaChi = it.MaDC.toString()
        }
    }

    if(diachi == null){
        Text("Không có giề")
    }
    else{
        DiaChi?.forEach { diaChi ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        RadioButton(
                            selected = (chonDiaChi == diaChi.MaDC.toString()),
                            onClick = { chonDiaChi = diaChi.MaDC.toString()},
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF003D24),
                                unselectedColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                        CardDiaChiNguoiDung(diaChi, MaND)
                    }
                }
            }
        }
    }

}