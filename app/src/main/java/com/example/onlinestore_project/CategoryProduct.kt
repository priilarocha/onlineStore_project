package com.example.onlinestore_project

import com.example.onlinestore_project.Constants.ELECTRONIC_IMAGE
import com.example.onlinestore_project.Constants.JEWELRY_IMAGE
import com.example.onlinestore_project.Constants.MENWEAR_IMAGE
import com.example.onlinestore_project.Constants.WOMENWEAR_IMAGE

data class CategoryProduct(
    val name: String,
    val image: String
)

fun getImages(): MutableList<String> {
    val newCategoryList = mutableListOf<String>()

    newCategoryList.add(ELECTRONIC_IMAGE)
    newCategoryList.add(JEWELRY_IMAGE)
    newCategoryList.add(MENWEAR_IMAGE)
    newCategoryList.add(WOMENWEAR_IMAGE)

    // below we have some frustrating code that we tried to use to get the images that I had imported to show up, instead of using image urls

//    val eletronic_image = R.drawable.eletronic
//    newCategoryList.add(eletronic_image)
//    val jewellery_image = R.drawable.jewelry
//    newCategoryList.add(jewellery_image)
//    val menwear_image = R.drawable.menwear
//    newCategoryList.add(menwear_image)
//    val womenwear_image = R.drawable.womenwear
//    newCategoryList.add(womenwear_image)

//    newCategoryList.add(R.drawable.eletronic.toString())
//    newCategoryList.add(R.drawable.jewelry.toString())
//    newCategoryList.add(R.drawable.menwear.toString())
//    newCategoryList.add(R.drawable.womenwear.toString())
    return newCategoryList
}

    // another attempt at getting the images to show up

//fun getImages(newCategoryList: String): Int {
//    return when (newCategoryList) {
//        "electronics" -> R.drawable.eletronic
//        "jewelery" -> R.drawable.jewelry
//        "men's clothing" -> R.drawable.menwear
//        "women's clothing" -> R.drawable.womenwear
//        else -> R.drawable.ic_launcher_background
//    }
//}