package com.myapplication.retrofitchatgpt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.myapplication.retrofitchatgpt.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the button
        binding.addButton.setOnClickListener {

            val retrofit = RetrofitClient.getInstance()
            val apiInterface = retrofit.create(CohereInterface::class.java)

            val question = binding.inputEdit.text.toString()

//            val call: Call<JsonObject> = apiInterface.getResponse("what's 1+1")
            val call: Call<JsonObject> = apiInterface.getResponse(question)

            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d("Sonu", response.body().toString())
                    Toast.makeText(
                        it.context,
                        "result: " + response.body().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.outputText.text = response.body().toString()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("Error", t.toString())
                }
            })


        }
    }
}
