package com.example.android_rom.data

import android.content.Context

object Graph{
    lateinit var db: ContactData
    private set
    val repository by lazy{
        ContactRepository(
            db.dao
        )
    }
    fun provide(context: Context){
        db = ContactData.getDatabase(context = context)
    }
}