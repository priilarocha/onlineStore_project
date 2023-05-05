package com.example.onlinestore_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.onlinestore_project.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Request
import okhttp3.Callback
import okhttp3.*
import java.io.IOException
import com.example.onlinestore_project.Auth
import com.example.onlinestore_project.Token
import com.example.onlinestore_project.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private val gson = Gson()
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sharedPrefs = getSharedPreferences("OnlineStore", Context.MODE_PRIVATE)
        // Recover the saved credentials from SharedPreferences
        val savedUsername = sharedPrefs.getString("username", null)
        val savedPassword = sharedPrefs.getString("password", null)
        if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            binding.username.setText(savedUsername)
            binding.password.setText(savedPassword)
        }

        binding.signIn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            signIn(username, password)
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, CreateAnAccount::class.java)
            startActivity(intent)
            finish()
        }
    }
        private fun signIn(username: String, password: String) {
            val auth = Auth(username, password)
            val request = getTokenAttachedRequestBuilder()
                .url("https://fakestoreapi.com/auth/login")
                .post(gson.toJson(auth).toRequestBody())
                .header("Content-Type", "application/json")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Log.i("MAIN_ACT", "Failure: $e")
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        if (response.isSuccessful) {
                            val responseString = response.body?.string()
                            val tokenObject = gson.fromJson(responseString, Token::class.java)

                            // Save the credentials in SharedPreferences
                            sharedPrefs.edit().apply {
                                putString("username", username)
                                putString("password", password)
                                apply()
                            }

                            runOnUiThread {
                                Log.i("MAIN_ACT", "Success: $responseString")
                                Log.i("MAIN_ACT", "Token: ${tokenObject.token}")
                                val intent = Intent(this@MainActivity, Categories::class.java)
                                startActivity(intent)
                                finish()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Unsuccessful ${response.code}",
                                    Toast.LENGTH_LONG
                                ).show()
                                Log.i("MAIN_ACT", "Unsuccessful ${response.code}")
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Log.e("MAIN_ACT", "Error: $e")
                        }
                    }
                }
            })
        }

    private fun getTokenAttachedRequestBuilder(): Request.Builder {
        val token = sharedPrefs.getString("token", "")
        val requestBuilder = Request.Builder()
        return if (token!!.isNotEmpty())
            requestBuilder.header("Authorization", "Bearer $token")
        else requestBuilder
    }
}