package com.example.login

import androidx.lifecycle.MutableLiveData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {
    fun getUserInfo(): MutableLiveData<UserInfo> {
        val userInfo = MutableLiveData<UserInfo>()

        val call = RetrofitManager.instance.api.getUserInfo()
        call.enqueue(object : Callback<UserInfo> {


            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val data = response.body()

                userInfo.value = data
            }
        })
        return userInfo
    }
}