package com.example.login

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {
    private val apiService: APIService
    private val Domain = "https://api.github.com/"
    private var logging = HttpLoggingInterceptor { message ->
        Log.e(
            "api",
            "interceptor msg: $message"
        )
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private var okHttpClient = OkHttpClient().newBuilder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).addInterceptor(logging).build()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Domain)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        apiService = retrofit.create(APIService::class.java)
    }

    val api: APIService
        get() = apiService

    companion object {
        val instance = RetrofitManager()
    }
}