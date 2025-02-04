package com.example.android_api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel: ViewModel(){
    var listMusic: List<Music> by mutableStateOf(emptyList())
    var musicAddResult by mutableStateOf("")
    var musicUpdateResult by mutableStateOf("")
    var music: Music by mutableStateOf(Music("", "", "", "", ""))

    fun getAllMusic(){
        viewModelScope.launch (Dispatchers.IO){
            try{
                listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
            }catch (e: Exception){
                Log.e("MusicViewModel", "Error getting music", e)
            }
        }
    }

    fun getMusic(id: String){
        viewModelScope.launch (Dispatchers.IO){
            try{
                //listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
                music = MusicRetrofitClient.musicAPIService.getMusic(id)
            }catch (e: Exception){
                Log.e("MusicViewModel", "Error getting music", e)
            }
        }
    }

    fun addMusic(music: Music){
        viewModelScope.launch (Dispatchers.IO){
            try{
                musicAddResult = MusicRetrofitClient.musicAPIService.addMusic(music).message()
            }catch (e: Exception){
                Log.e("MusicViewModel", "Error add music", e)
            }
        }
    }

    fun updateMusic(musicId: String, music: Music){
        viewModelScope.launch (Dispatchers.IO){
            try{
                musicUpdateResult = MusicRetrofitClient.musicAPIService.updateMusic(musicId, music).message()
                listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
            }catch (e: Exception){
                Log.e("MusicViewModel", "Error update music", e)
            }
        }
    }

    fun deleteMusic(musicId: String){
        viewModelScope.launch (Dispatchers.IO){
            try{
                MusicRetrofitClient.musicAPIService.deleteMusic(musicId)
                listMusic = MusicRetrofitClient.musicAPIService.getAllMusic()
            }catch (e: Exception){
                Log.e("MusicViewModel", "Error delete music", e)
            }
        }
    }

    fun newMusic(): Int{
        if (listMusic.isEmpty()) return 1
        return listMusic.maxOf { it.id.toInt() } + 1
    }
}