package com.example.android_datastore.screen

import android.widget.RadioButton
import android.widget.Switch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_datastore.UserStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// min -> target -> compile
@Composable
fun SettingScreen(){
    val context = LocalContext.current
    val store = UserStore(context)
    val checked = store.getSettingColor.collectAsState(initial = false)
    val checkedBg = store.getSettingBgColor.collectAsState(initial = false)


    Column (
        modifier = Modifier.fillMaxSize().background(color =
            if(checkedBg.value){
                Color.White
            }
            else{
                Color.Black
            }
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        if(checked.value){
            Text(
                text = "Setting Screen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
        else{
            Text(
                text = "Setting Screen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if(checkedBg.value){
                    Color.Black
                }
                else{
                    Color.White
                }
            )
        }


        SwitchMinimal(store)
        Box(modifier = Modifier.height(20.dp))
        Text(
            text = "Setting Background",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if(checkedBg.value){
                Color.Black
            }
            else{
                Color.White
            }
        )
        RadioBackground(store)

    }
}

@Composable
fun SwitchMinimal(store: UserStore){
    val checked = store.getSettingColor.collectAsState(initial = false)
    val checkedBg = store.getSettingBgColor.collectAsState(initial = false)

    Row (
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            "Black",
            fontWeight = FontWeight.Bold,
            color = if(checkedBg.value){
                Color.Black
            }
            else{
                Color.White
            }
        )
        Switch(
            checked = checked.value,
            onCheckedChange = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveSettingColor(it)
                }
            }
        )
        Text("Red", fontWeight = FontWeight.Bold, color = Color.Red)
    }



}

@Composable
fun RadioBackground(store: UserStore){
    val checkedBg = store.getSettingBgColor.collectAsState(initial = false)

    Row(
        Modifier.selectableGroup().fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("White",
            fontWeight = FontWeight.Bold,
            color = if(checkedBg.value){
                Color.Black
            }
            else{
                Color.White
            }
        )
        RadioButton(
            selected = checkedBg.value,
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveSettingBackground(true)
                } },
            modifier = Modifier.semantics { contentDescription = "Localized Description" }
        )
        Text(
            "Black",
            fontWeight = FontWeight.Bold,
            color = if(checkedBg.value){
                Color.Black
            }
            else{
                Color.White
            }
        )
        RadioButton(
            selected = !checkedBg.value,
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveSettingBackground(false)
                }
            },
            modifier = Modifier.semantics { contentDescription = "Localized Description" }
        )
    }

}