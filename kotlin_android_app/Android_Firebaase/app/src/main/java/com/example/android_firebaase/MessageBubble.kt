package com.example.android_firebaase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageBubble(message: String = "", isMe: Boolean = true){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalAlignment = if(isMe) Alignment.End else Alignment.Start
    ){
        Card (
            colors = CardDefaults.cardColors(
                containerColor = if(isMe) Color.Blue else Color.White,
                contentColor = if(isMe) Color.White else Color.Black
            ),
            shape = if (isMe)
                RoundedCornerShape(
                    topStartPercent = 20,
                    bottomEndPercent = 20,
                    bottomStartPercent = 20
                )
            else
                RoundedCornerShape(
                    topEndPercent = 20,
                    bottomEndPercent = 20,
                    bottomStartPercent = 20
                ),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ){
            Text(
                text = message, softWrap = true,
                modifier = Modifier.padding(5.dp)
            )
        }
    }

}