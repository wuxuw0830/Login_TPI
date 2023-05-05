package com.example.login

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIService {
    @GET("users")
     fun getUserInfo(): Call<List<UserInfo>>
}