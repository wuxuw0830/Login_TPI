package com.example.login

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val EMPTY=""
class MainViewModel : ViewModel() {
    var data = MutableLiveData<List<UserInfo>>()
    val recoverError = MutableLiveData<Unit>()
    fun getUserInfo(){
        val result=MainRepository.getUserInfo()
        result.enqueue(object : Callback<List<UserInfo>> {


            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<UserInfo>>, response: Response<List<UserInfo>>) {
                data.postValue(response.body())
            }
        })
    }
}


@Parcelize
data class UserInfo(
    val login: String= EMPTY,
    val id: Int,
    val node_id: String= EMPTY,
    val avatar_url: String= EMPTY,
    val gravatar_id: String= EMPTY,
    val url: String= EMPTY,
    val html_url: String= EMPTY,
    val followers_url: String= EMPTY,
    val following_url: String= EMPTY,
    val gists_url: String= EMPTY,
    val starred_url: String= EMPTY,
    val subscriptions_url: String= EMPTY,
    val organizations_url: String= EMPTY,
    val repos_url: String= EMPTY,
    val events_url: String= EMPTY,
    val received_events_url: String= EMPTY,
    val type: String= EMPTY,
    val site_admin: Boolean = false
):Parcelable