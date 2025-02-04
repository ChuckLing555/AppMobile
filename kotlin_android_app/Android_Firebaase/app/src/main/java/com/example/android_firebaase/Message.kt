package com.example.android_firebaase

data class Message (
    val sender: String = "",
    val receiver: String = "",
    val content: String = "",
    val timeline: Long = System.currentTimeMillis()
)