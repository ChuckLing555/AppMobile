package com.example.android_datastore.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_datastore.User
import com.example.android_datastore.UserStore
import com.example.android_datastore.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen (navController: NavController){
    val context = LocalContext.current
    val tokenValue = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    val store = UserStore(context)
    val tokenText = store.getAccessToken.collectAsState(initial = "")

    Column (
        modifier = Modifier.padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "DEMO APP",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        OutlinedTextField(
            value = email.value,
            label = { Text("Email") },
            onValueChange = { email.value = it}
        )

        Box(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            label = { Text("Password") },
            onValueChange = { password.value = it}
        )

        Box(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveUserInfor(
                        User(
                            email = email.value.text,
                            password = password.value.text,
                            user_token = password.value.text
                        )
                    )
                }
                if(tokenText.value.isNotEmpty()){
                    navController.navigate(Screens.Home.route)
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text("Login", fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}