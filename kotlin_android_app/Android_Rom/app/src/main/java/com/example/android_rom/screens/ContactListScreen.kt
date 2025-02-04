
package com.example.android_rom.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_rom.data.ContactListVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavHostController){
    var viewModel: ContactListVM = viewModel(
        modelClass = ContactListVM::class.java
    )
    var contactListState = viewModel.state
    var onSearch: () -> Unit = {
        navController.navigate(NavRoute.Search_Screen.route)
    }

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth().clickable (
                            onClick = onSearch
                        ),
                        placeholder = { Text(text = "Tìm kiếm") },
                        leadingIcon = {
                            IconButton(onClick = onSearch) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = ""
                                )
                            }
                        },
                        trailingIcon = {
                            Row {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Rounded.QrCode,
                                        contentDescription = ""
                                    )
                                }
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Rounded.Settings,
                                        contentDescription = ""
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.LightGray,
                            unfocusedContainerColor = Color.LightGray
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
            item {
                CardInfo(name = "Bạch dạ hành", phone = "00011", {}, {})
            }
            item {
                CardInfo(name = "Phong Thần", phone = "00010", {}, {})
            }
            items(contactListState.contacts){con ->
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
                    onDelete = {viewModel.deleteContact(con)}
                )
            }
        }
    }
}