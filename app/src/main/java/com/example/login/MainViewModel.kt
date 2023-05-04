package com.example.login

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.parcelize.Parcelize


class MainViewModel : ViewModel() {
    var response = MutableLiveData<UserInfo>()
    val recoverError = MutableLiveData<Unit>()
    fun getUserInfo(): MutableLiveData<UserInfo> {
        response = MainRepository.getUserInfo()
        return response
    }
}

@Parcelize
data class UserInfo(
    val login: String?,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
):Parcelable