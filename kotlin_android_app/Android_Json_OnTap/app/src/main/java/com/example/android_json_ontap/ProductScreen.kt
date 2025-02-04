package com.example.android_json_ontap

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    // Bien dau vao
    navHostController: NavHostController,
    viewModel: ProductViewModel
){
    var products by remember { mutableStateOf(viewModel.getProducts()) }
    val activity = (LocalContext.current as? Activity)

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            "MEMU",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                activity?.finishAndRemoveTask()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White
                    )
                )
            }
        }
    ){
        paddingValue ->
        LazyColumn (modifier = Modifier.padding(paddingValue)){
            items(products){
                ProductCardScreen(
                    product = it,
                    clicktodetail = {
                        navHostController.navigate(Screen.ProductDetailScreen.route + "?id=${it.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ProductCardScreen(product: Product, clicktodetail: () -> Unit){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = clicktodetail
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = product.picture,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(product.name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(product.price.toString())
            }
        }
    }
}