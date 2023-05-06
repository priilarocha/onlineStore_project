package com.example.onlinestore_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.onlinestore_project.databinding.ActivityProductListBinding
import com.example.onlinestore_project.Retrofit.getData


class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private val categoriesList = mutableListOf<CategoryProduct>()
    private val adapterCategory by lazy {
        CategoryAdapter(categoriesList, this, goToDetails())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCategory()

        binding.categoryListRecyclerView.adapter = adapterCategory
        binding.categoryListRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getCategory() {
        getData().getCategory().enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                val data = response.body()
                data?.let {
                    val category = it.mapIndexed { index, name ->
                        CategoryProduct(
                            name = name,
                            image = getImages()[index]
                        )
                    }
                    categoriesList.addAll(category)
                    adapterCategory.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(this@ProductListActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun goToDetails(): Click {
        return object : Click {
            override fun onClick(name: String) {
                // when click on category, go to ProductDetailsActivity
                val intent = Intent(this@ProductListActivity, ProductDetailsActivity::class.java)
                intent.putExtra("Extra", name)
                startActivity(intent)
            }

        }
    }
}