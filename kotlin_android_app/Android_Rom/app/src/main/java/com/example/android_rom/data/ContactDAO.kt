package com.example.android_rom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDAO{
    @Query("Select * From Contact")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("Select * From Contact Where Id = :contactId")
    fun getContactById(contactId: Int): Flow<Contact>

    @Query("Select * From Contact Where FullName like '%' || :value || '%' or Phone = :value")
    fun searchContact(value: String): Flow<List<Contact>>

    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}