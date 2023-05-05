package com.example.onlinestore_project

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Callback
import okhttp3.Call
import java.io.IOException
import org.json.JSONArray

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoriesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        supportActionBar?.hide()


        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://fakestoreapi.com/products/categories")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()

                runOnUiThread {
                    try {
                        val json = JSONArray(responseBody)
                        val categories = mutableListOf<String>()
                        for (i in 0 until json.length()) {
                            categories.add(json.getString(i))
                        }
                        val adapter = ArrayAdapter(
                            this@CategoryActivity,
                            android.R.layout.simple_list_item_1,
                            categories
                        )

                        categoriesListView.adapter = adapter

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}
