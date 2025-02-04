package com.example.android_rom.data

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactDetailVM(
    private val contactId: Int,
    private val contactRepository: ContactRepository = Graph.repository
) :ViewModel() {
    var state by mutableStateOf(ContactState())
        private set
    init{
        if(contactId > 0){
            viewModelScope.launch {
                contactRepository.getContactByID(contactId).collectLatest{
                    state = state.copy(
                        fullname = it.FullName,
                        email = it.Email,
                        phone = it.Phone
                    )
                }
            }
        }
    }

    fun onChangeFullName(newValue: String){
        state = state.copy(fullname = newValue)
    }

    fun onChangePhone(newValue: String){
        state = state.copy(phone = newValue)
    }

    fun onChangeEmail(newValue: String){
        state = state.copy(email = newValue)
    }

    fun insertContact(contact: Contact){
        viewModelScope.launch {
            contactRepository.insertContact(contact = contact)
        }
    }

    fun updateContact(contact: Contact){
        viewModelScope.launch {
            contactRepository.updateContact(contact = contact)
        }
    }

//    fun deleteContact(contact: Contact){
//        viewModelScope.launch {
//            contactRepository.deleteContact(contact = contact)
//        }
//    }

}


@Suppress("UNCHECKED_CAST")
class ContactDetailVMFactor(private val id: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactDetailVM(contactId = id) as T
    }
}

data class ContactState(
    val fullname: String = "",
    val phone: String = "",
    val email: String = ""
)