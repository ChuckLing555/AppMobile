package com.example.android_kt

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

class BookViewModel (private var context: Context): ViewModel(){
    private var books : List<Book> = emptyList()

    fun readJsonFromFile(context: Context, filename: String): String{
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }

    fun getListBook() : List<Book>{
        if(books. isEmpty()){
            val json = readJsonFromFile(context, "bookstore.json")
            val type = object : TypeToken<List<Book>> () {} .type
            books = Gson().fromJson(json, type)
        }
        return books
    }

    fun getBookById(id: Int): Book{
        return books.find { it.id == id }?: books[0]
    }
}