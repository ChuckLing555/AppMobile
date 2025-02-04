package com.example.lizardswine.Data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class User(
    val userID: Int,
    val user_token: String
)

class UserStore (private val context: Context){
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
        private val USER_ID = intPreferencesKey("user_id")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
    }

    suspend fun saveUserInfor(user: User){
        context.dataStore.edit {
            it[USER_ID] = user.userID
            it[USER_TOKEN_KEY] = user.user_token
        }
    }

    fun getUser() = context.dataStore.data.map {
        User(
            userID = it[USER_ID] ?: -1,
            user_token = it[USER_TOKEN_KEY] ?: ""
        )
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map {
            preferences -> preferences[USER_TOKEN_KEY] ?: ""
    }

    suspend fun logoutUser(){
        context.dataStore.edit { it.clear() }
    }

}