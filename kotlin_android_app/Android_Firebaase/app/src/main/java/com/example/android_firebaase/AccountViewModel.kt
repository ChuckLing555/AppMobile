package com.example.android_firebaase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class AccountViewModel : ViewModel(){
    var state by mutableStateOf(AccountState())
        private set

    fun addUser(){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener {
                if( !it.isSuccessful){
                    state = state.copy(success = false)
                }
                else {
                    val user = User(
                        state.email,
                        state.password,
                        state.fullname
                    )
                    Firebase.firestore.collection("users").add(user)
                    state = state.copy(success = true)
                }
            }
    }

    fun signIn() {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener {
                state = state.copy(success = it.isSuccessful)
            }
    }

    fun onChangeFullName(newValue: String){
        state = state.copy(fullname = newValue)
    }
    fun onChangeEmail(newValue: String){
        state = state.copy(email = newValue)
    }
    fun onChangePassword(newValue: String){
        state = state.copy(password = newValue)
    }

}

data class AccountState(
    val fullname: String = "",
    val email: String = "",
    val password: String = "",
    val success: Boolean = false,
)