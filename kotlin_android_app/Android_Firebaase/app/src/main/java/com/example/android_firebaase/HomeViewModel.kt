package com.example.android_firebaase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(){
    var state by mutableStateOf(HomeScreenState())
        private set
    init {
        getAllContact()
    }

    fun getAllContact(){
        viewModelScope.launch {
            var ls = mutableListOf<User>()
            if(Firebase.auth.currentUser != null){
                Firebase.firestore.collection("users")
                    .whereNotEqualTo("email", Firebase.auth.currentUser?.email)
                    .addSnapshotListener { value, error ->
                        if (value != null) {
                            for(doc in value){
                                var user = doc.toObject(User::class.java)
                                ls.add(user)
                            }
                        }
                        state = state.copy(
                            contactList = ls
                        )
                    }
            }
        }
    }

    fun SignOut(){
        FirebaseAuth.getInstance().signOut()
    }
}

data class HomeScreenState(
    val contactList: List<User> = emptyList()
)