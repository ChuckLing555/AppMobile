package com.example.android_rom.data

class ContactRepository(private val contactDAO: ContactDAO) {
    val contacts = contactDAO.getAllContacts()

    fun getContactByID(id: Int) = contactDAO.getContactById(id)

    fun searchContact(NameorPhone: String) = contactDAO.searchContact(NameorPhone)

    suspend fun deleteContact(contact: Contact){
        contactDAO.deleteContact(contact)
    }

    suspend fun insertContact(contact: Contact){
        contactDAO.insertContact(contact)
    }

    suspend fun updateContact(contact: Contact){
        contactDAO.updateContact(contact)
    }
}