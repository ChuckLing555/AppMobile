package com.example.android_rom.data


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactSearchVM(
    private val contactRepository: ContactRepository = Graph.repository
): ViewModel() {
    var state by mutableStateOf(ContactSearchState())
        private set

    init{
        getAllContact()
    }

    private fun getAllContact(){
        viewModelScope.launch {
            contactRepository.contacts.collectLatest{
                state = state.copy(
                    contactsSearch = it
                )
            }
        }
    }

    fun onChangeSearch(newvalue: String){
        state = state.copy(
           _search = newvalue
        )
        viewModelScope.launch {
            contactRepository.searchContact(state._search).collectLatest {
                state = state.copy(
                    contactsSearch = it
                )
            }
        }
    }

}

data class  ContactSearchState(
    val contactsSearch: List<Contact> = emptyList(),
    //val contactFound: Contact = Contact(),
    val _search: String = ""
)
