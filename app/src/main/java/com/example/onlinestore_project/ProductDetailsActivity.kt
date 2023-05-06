package com.example.onlinestore_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.onlinestore_project.databinding.ActivityProductDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private var extraIntent:String? = ""
    private val detailsCategoryList = mutableListOf<Product>()
    private val detailsCategoryAdapter: DetailsCategoryAdapter by lazy {
        DetailsCategoryAdapter( detailsCategoryList, this, goToDetailsProducts())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        extraIntent = intent.getStringExtra("Extra")
        getDetails()

        binding.apply {
            detailsProducts.adapter = detailsCategoryAdapter
            detailsProducts.layoutManager = GridLayoutManager(this@ProductDetailsActivity, 2)

        }
    }

    private fun getDetails(){
        extraIntent?.let {
            Retrofit.getData().getDetails(it).enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    val data = response.body()
                    data?.let {
                        detailsCategoryList.addAll(it)
                    }
                    detailsCategoryAdapter.notifyDataSetChanged()
                    Log.e("Success", data.toString())
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    t.localizedMessage?.let { it1 -> Log.e("error", it1) }
                }


            })
        }
    }
    private fun goToDetailsProducts():OnClick{
        return object : OnClick {
            override fun detailsProduct(idProduct: Int) {
                // when click on item, go to DetailProductActivity
                val intent = Intent(this@ProductDetailsActivity, DetailProductActivity::class.java)
                intent.putExtra("idProduct", idProduct)
                startActivity(intent)
            }
        }
    }
}
