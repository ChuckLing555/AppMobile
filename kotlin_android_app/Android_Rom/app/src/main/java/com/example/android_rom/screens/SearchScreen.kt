package com.example.android_rom.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.android_rom.data.ContactListVM
import com.example.android_rom.data.ContactSearchVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactSearchScreen(navController: NavHostController){
    var viewModel: ContactSearchVM = viewModel(
        modelClass = ContactSearchVM::class.java
    )
    var contactListState = viewModel.state

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.LightGray
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = ""
                        )
                    }
                },
                title = {
                    TextField(
                        value = contactListState._search,
                        onValueChange = viewModel::onChangeSearch,
                        placeholder = { Text(text = "Tìm kiếm") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        )
                    )
                }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = {
                    // chuyen man hinh
                    navController.navigate(NavRoute.Detail_Screen.route)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                ),
                modifier = Modifier.clip(CircleShape)
            ){
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ){
        LazyColumn (
            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(contactListState.contactsSearch){con ->
                con
                CardInfo(
                    name = con.FullName,
                    phone = con.Phone,
                    onClickCard = {
                        navController.navigate(
                            NavRoute.Detail_Screen.route + "?id=${con.Id}"
                        )
                    },
                    //id = con.Id,
                    onDelete = {}
                )
            }
        }
    }
}

