package com.example.onlinestore_project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/products/categories")
    fun getCategory(): Call<List<String>>

    @GET("products/category/{category}")
    fun getDetails(@Path("category") category:String): Call<List<Product>>

    @GET("/products/{numberProduct}")
    fun getProduct(@Path("numberProduct") numberProduct:Int): Call<Product>
}