package com.example.android_json

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.text.SimpleDateFormat

class NotificationVM(
    private val context: Context
) : ViewModel() {
    var notifications : List<Notification> = emptyList()

    fun getNotification(): List<Notification>{
        if(notifications.isEmpty()){
            val json = readJsonFromFile(context, "notifications.json")
            val type = object : TypeToken<List<Notification>>(){}.type
            notifications = Gson().fromJson(json, type)
        }
        return notifications
    }

    fun getEmptyList(): List<Notification>{
        return emptyList()
    }

    fun readJsonFromFile(context: Context, fileName: String): String{
        val inputStream = context.assets.open(fileName)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }

    fun sortNotificationByOldest(): List<Notification>{
        var sortNotification: List<Notification> = emptyList()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        if(getNotification().isNotEmpty()){
            sortNotification = getNotification().sortedBy {
                notifications -> dateFormat.parse(notifications.time)
            }
        }
        return sortNotification
    }

    fun sortNotificationByLastest(): List<Notification>{
        return sortNotificationByOldest().reversed()
    }

    fun searchNotification(_value: String): List<Notification>{
        return if(_value.isEmpty()) getNotification()
        else{
            getNotification().filter {
                it.content.contains(_value, ignoreCase = true) || it.title.contains(_value, ignoreCase = true)
            }
        }
    }

    fun getNotificationByID(id: Int): Notification {
        return notifications.find { it.id == id } ?: notifications[0]
    }
}
