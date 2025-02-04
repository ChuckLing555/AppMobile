package com.example.android_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore (private val context: Context){
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
        private val EMAIL = stringPreferencesKey("Email")
        private val PASSWORD = stringPreferencesKey("Password")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        private val SETTING_COLOR = booleanPreferencesKey("setting_color")
        private val SETTING_BACKGROUND_COLOR = booleanPreferencesKey("setting_background_color")
    }

    suspend fun saveUserInfor(user: User){
        context.dataStore.edit {
            it[EMAIL] = user.email
            it[PASSWORD] = user.password
            it[USER_TOKEN_KEY] = user.user_token
        }
    }

    suspend fun saveSettingColor(checked: Boolean){
        context.dataStore.edit {
            it[SETTING_COLOR] = checked
        }
    }

    suspend fun saveSettingBackground(checked: Boolean){
        context.dataStore.edit {
            it[SETTING_BACKGROUND_COLOR] = checked
        }
    }

    fun getUser() = context.dataStore.data.map {
        User(
            email = it[EMAIL] ?: "",
            password = it[PASSWORD] ?: "",
            user_token = it[USER_TOKEN_KEY] ?: ""
        )
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map {
        preferences -> preferences[USER_TOKEN_KEY] ?: ""
    }
    val getSettingColor: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[SETTING_COLOR] ?: false
    }
    val getSettingBgColor: Flow<Boolean> = context.dataStore.data.map {
            preferences -> preferences[SETTING_BACKGROUND_COLOR] ?: false
    }

    suspend fun logoutUser(){
        context.dataStore.edit { it.clear() }
    }

}