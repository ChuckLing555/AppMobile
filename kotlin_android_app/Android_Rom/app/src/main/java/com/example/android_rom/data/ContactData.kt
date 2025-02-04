package com.example.android_rom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactData : RoomDatabase(){
    abstract val dao: ContactDAO

    companion object{
        @Volatile
        private var Insance: ContactData? = null

        fun getDatabase(context: Context): ContactData{
            return Insance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    ContactData::class.java,
                    "contact_db"
                )
                    .build().also { Insance = it }
            }
        }
    }
}