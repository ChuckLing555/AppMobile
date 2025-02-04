package com.example.android_datastore.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_datastore.UserStore
import com.example.android_datastore.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController){
    val context = LocalContext.current
    val store = UserStore(context)
    val user = store.getUser().collectAsState(initial = null)

    Column (
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Welcome! \n${user.value?.email}\n to my application",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    // chuyen trang
                    navController.navigate(Screens.Setting.route)
                }
            ) {
                Text(
                    text = "Go to Setting",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        store.logoutUser()
                    }
                    // chuyen trang
                    navController.navigate(Screens.Login.route)
                }
            ) {
                Text(
                    text = "Logout",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}