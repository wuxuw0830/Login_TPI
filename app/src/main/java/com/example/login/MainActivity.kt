package com.example.login

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        /**
         * 注意:請全程使用MVVM的架構，需要 ViewModel, Repository
         * 最重要的是可以Build出來，不論功能有沒有全部完成。
         */

        binding.btnLogin.setOnClickListener {
            /** 題目 1
             * 寫一個簡單登入流程，此流程不會需要呼叫API
             * 畫面已經建好EditText和Button, et_username, et_password, btn_login
             *
             * 按下登錄按鈕後(btn_login)，進行登入檢核
             * 登入檢核有下面的條件
             * 1. 帳號和密碼不能一樣
             * 2. 密碼需要英數混合，不用分大小寫
             * 3. 密碼最少要六個字
             * 登入成功後保存帳密到SharedPreferences並加密
             * 登入成功用Toast顯示<登入成功>文字，登入失敗Toast則顯示<登入失敗>文字
             * */
            setLoginEvent()
        }

        binding.btnGetUserInfo.setOnClickListener {
            /**
             * 題目 2
             * 畫面上已經建好TextView和Button, btn_get_user_info, tv_user_info
             * 按下顯示用戶資料的按鈕後(btn_get_user_info)，使用下面的Github網址取得Github用戶資料
             * https://api.github.com/users
             * 把取得的第一筆資料顯示在tv_user_info裡面，顯示json格式就行了
             * */
            mainViewModel.getUserInfo()
        }
        binding.etPassword.filters = arrayOf(CommonFilter())
        mainViewModel.response.observe(this, Observer {
            binding.tvUserInfo.text = it.toString()
        })
    }

    private fun setLoginEvent() {
        binding.apply {
            when {
                etUsername == etPassword -> toast(isLoginSuccess = false)
                etPassword.text.isNullOrEmpty() || etUsername.text.isNullOrEmpty() -> toast(
                    isLoginSuccess = false
                )

                etPassword.length() < 6 -> toast(isLoginSuccess = false)
                else -> toast()
            }

        }
    }


    private fun toast(isLoginSuccess: Boolean = true) =
        Toast.makeText(
            this@MainActivity,
            if (isLoginSuccess) LOGIN_SUCCESS else LOGIN_FAILED,
            Toast.LENGTH_SHORT
        ).show().also {
            if (isLoginSuccess) {
                val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
                val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
                val sharedPrefsFile: String = "Login_Pref"
                val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
                    sharedPrefsFile,
                    mainKeyAlias,
                    applicationContext,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )

                with(sharedPreferences.edit()) {
                    putString("Account", binding.etUsername.text.toString())
                    putString("Pwd", binding.etPassword.text.toString())
                    apply()
                }
            }
        }


    companion object {
        private const val LOGIN_SUCCESS = "登入成功"
        private const val LOGIN_FAILED = "登入失敗"

    }
}
