package com.myapplication.retrofitchatgpt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // for pc emulator
    val Base_Url = "http://10.0.2.2:5000/"
    // for real device
//    val Base_Url = "http://127.0.0.1:5000/"

    fun getInstance(): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_Url)
            .build()
        return retrofit
    }
}