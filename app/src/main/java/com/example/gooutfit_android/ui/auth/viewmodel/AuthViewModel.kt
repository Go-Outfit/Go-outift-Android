package com.example.gooutfit_android.ui.auth.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("login_pref", Context.MODE_PRIVATE)

    private val _idToken = MutableLiveData<String>()
    val idToken: LiveData<String> = _idToken

    fun login(email: String, password: String) {
        val authRequest = AuthRequest()
        authRequest.email = email
        authRequest.password = password

        val client = ApiAuthConfig.getApiService()
        client.login(authRequest).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.e("AuthViewModel", "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.data?.idToken?.let {
                        _idToken.value = it
                        saveTokenToSharedPreferences(it)
                    }
                } else {
                    Log.e("AuthViewModel", "onFailure: ${response.message()}")
                }
            }
        })
    }

    private fun saveTokenToSharedPreferences(token: String?) {
        // Inisialisasi Shared Preferences
        val editor = sharedPreferences.edit()

        // Menyimpan token ke Shared Preferences
        editor.putString("token", token)
        editor.apply()
    }
}