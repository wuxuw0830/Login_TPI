package com.example.login

import android.util.Log
import androidx.lifecycle.MutableLiveData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {
    fun getUserInfo(): Call<List<UserInfo>> {
        return RetrofitManager.instance.api.getUserInfo()
    }
}