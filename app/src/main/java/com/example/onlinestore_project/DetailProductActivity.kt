package com.example.onlinestore_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinestore_project.databinding.ActivityDetailProductBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idProduct = intent.getIntExtra("idProduct", 0)

        getProductDetails(idProduct)
    }

    private fun getProductDetails(idProduct: Int) {
        Retrofit.getData().getProduct(idProduct).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val data = response.body()
                binding.apply {
                    data?.let {
                        Picasso.get().load(it.image).into(productImage)
                        productTitle.text = it.title
                        productDescription.text = it.description
                    }
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}
