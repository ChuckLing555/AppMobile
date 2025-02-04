package com.example.android_json_ontap

import android.widget.NumberPicker
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navHostController: NavHostController,
    viewModel: ProductViewModel,
    id: Int = -1
){
    var product by remember { mutableStateOf(viewModel.getProductById(id)) }
    var quantity by remember { mutableStateOf("1") }
    var temp: Int = 0
    var mess: String = ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Đặt Món",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            var localConfig = LocalConfiguration.current
            Box( //hình ảnh chiếm 30% độ cao màn hình bên trong Scaffold
                modifier = Modifier
                    .height((localConfig.screenHeightDp * 3 / 10).dp)
                    .fillMaxSize().background(color = Color.White)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()

                        .align(Alignment.Center),

                    model = product.picture,
                    contentDescription = ""
                )
            }
            Box(
//phần chi tiết khối màu xanh chiếm 70% (cách lề trên 30%)
                modifier = Modifier
                    .padding(
                        top = (localConfig.screenHeightDp * 3 / 10).dp
                    )

                    .fillMaxSize()
                    .clip(

                        shape = RoundedCornerShape(
                            topStartPercent = 8,
                            topEndPercent = 8

                        )
                    )

                    .background(color = Color.LightGray),

                ) {
                Column(//chỉ hiển thị nội dung trong 80% của khối xanh
                    modifier = Modifier
                        .height(
                            (LocalConfiguration.current.screenHeightDp * 8 / 10).dp
                        )
                        .verticalScroll(ScrollState(0))
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = product.name,
                        fontSize = 25.sp,

                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Text(
                        text = product.price.toString() + " VNĐ",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right,
                        color = Color.Red
                    )
                    Text(
                        text = product.description,
                        softWrap = true,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.DarkGray
                    )

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Số lượng: ",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left
                        )
                        OutlinedTextField(
                            value = quantity,
                            onValueChange = { quantity = it },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            maxLines = 1
                        )

                        if(quantity.isEmpty()) {
                            mess = "Không được để trống dữ liệu"
                            temp = 0
                        }
                        else
                            temp = quantity.toInt()

                        Column {
                            IconButton(
                                onClick = {
                                    temp += 1
                                    quantity = temp.toString();
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                            IconButton(
                                onClick = {
                                    temp -= 1
                                    quantity = temp.toString();
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = null
                                )
                            }
                        }
                   }
                    Text(
                        text = if(temp < 0) "Số lượng không được nhỏ hơn 0" else mess,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Row (
                        modifier = Modifier.fillMaxWidth().height(100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        Text(
                            text = "Thành tiền: ",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            text = if(temp > -1) "${temp * product.price}" + "" else "0 VNĐ",
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }

                }
            }
        }
    }
}