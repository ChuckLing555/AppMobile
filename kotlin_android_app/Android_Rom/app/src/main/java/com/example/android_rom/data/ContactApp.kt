package com.example.android_rom.data

import android.app.Application

class ContactApp: Application() {
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}