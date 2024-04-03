package com.example.graphql_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
    }

    private fun fetchData() {
        val paramObject = JsonObject()
        paramObject.addProperty("query", "")
        val retrofitService = RetrofitService.getInstance()


        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = retrofitService.getData(paramObject)

                // Process the response in UI thread
                CoroutineScope(Dispatchers.Main).launch {
                    // Handle the response
                    handleResponse(response)
                }
            } catch (e: Exception) {
                // Handle failure
                e.printStackTrace()
            }
        }
    }
    private fun handleResponse(response: CustomObject) {
        val userData = response.userData
        val user = userData.user

        findViewById<TextView>(R.id.textViewUserId).text = "User ID: ${user.id}"
        findViewById<TextView>(R.id.textViewUserName).text = "Name: ${user.name}"
        findViewById<TextView>(R.id.textViewUserEmail).text = "Email: ${user.email}"
    }
}