package com.example.android_json


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNotificationScreen(
    // Muc 1, bien dau vao
    navController: NavHostController,
    viewModel: NotificationVM
){
    // Muc 2, tham so

    var _search by remember { mutableStateOf("") }

    var notifications by remember {
        mutableStateOf(viewModel.getEmptyList())
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Notification",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().clickable {},
                    value = _search,
                    onValueChange = {
                        _search = it
                        if(_search.isEmpty()) notifications = viewModel.getEmptyList()
                        else
                            notifications = viewModel.searchNotification(_search)
                    },
                    placeholder = {
                        Text(
                            text ="Search Notifications"
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = {navController.popBackStack()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = ""
                            )
                        }
                    },
                )
            }
        }

    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier.padding(paddingValues)
        ){
            items(notifications){
                SearchNotificationCard(
                    notification = it,
                    onClickCard = {
                        navController.navigate(
                            NavRoute.DetailNotificationScreen.route + "?id=${it.id}"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SearchNotificationCard(notification: Notification, onClickCard: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClickCard

    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            // Muc 4
            AsyncImage(
                model = notification.imageURL,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if(notification.title.length > 18) notification.title.substring(0, 18) + "..."
                        else notification.title,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if(notification.content.length > 27) notification.content.substring(0, 27) + "..."
                    else notification.content,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = notification.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}