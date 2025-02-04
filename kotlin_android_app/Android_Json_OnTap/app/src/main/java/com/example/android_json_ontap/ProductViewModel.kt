package com.example.android_json_ontap

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

class ProductViewModel (private val context: Context) : ViewModel(){

    private var products: List<Product> = emptyList()

    fun getProducts(): List<Product>{
        if(products.isEmpty()){
            val json = readJsonFromFile(context, "ontap.json")
            val type = object : TypeToken<List<Product>>() {}.type
            products = Gson().fromJson(json, type)
        }
        return products
    }

    fun readJsonFromFile(context: Context, filename: String): String{
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }

    fun getProductById(id: Int) : Product {
        return products.find { it.id == id } ?: products[0]
    }
}