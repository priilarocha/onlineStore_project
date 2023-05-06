package com.example.onlinestore_project

import java.io.Serializable

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    var qtd: String
) : Serializable

data class Category(
    val id: Int,
    val name: String
) : Serializable
