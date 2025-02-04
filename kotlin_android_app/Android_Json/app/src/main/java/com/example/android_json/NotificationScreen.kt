package com.example.android_json


import android.content.Context
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    // Muc 1, bien dau vao
     navController: NavHostController,
     viewModel: NotificationVM
){
    // Muc 2, tham so

    var notifications by remember {
        mutableStateOf(viewModel.sortNotificationByLastest())
    }
    var sortByOnldest by remember {
        mutableStateOf(false)
    }

    var onSearch: () -> Unit = {
        navController.navigate(NavRoute.SearchNotificationScreen.route)
    }
    //test
//    val notifications = listOf<Notification>(
//        Notification("1 Con thằn lằn", "Lizard Information", "", "30-10-2024"),
//        Notification("2 Con thằn lằn", "Lizard Information", "", "30-10-2024"),
//        Notification("3 Con thằn lằn", "Lizard Information", "", "30-10-2024"),
//        Notification("4 Con thằn lằn", "Lizard Information", "", "30-10-2024"),
//        Notification("5 Con thằn lằn", "Lizard Information", "", "30-10-2024")
//    )

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
                    },
                    actions = {
                        Checkbox(
                            // Muc 3
                            checked = sortByOnldest,
                            onCheckedChange = {
                                if(sortByOnldest){
                                    notifications = viewModel.getNotification()
                                    sortByOnldest = false
                                }
                                else{
                                    notifications = viewModel.sortNotificationByOldest()
                                    sortByOnldest = true
                                }
                            }
                        )
                        Text(
                            text = "Cũ nhất"
                        )
                    }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().clickable(onClick = onSearch),
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text ="Search Notifications"
                        )
                    },
                    leadingIcon = {
                        IconButton(onClick = onSearch) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
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
                 NotificationCard(
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
fun NotificationCard(notification: Notification, onClickCard: () -> Unit){
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