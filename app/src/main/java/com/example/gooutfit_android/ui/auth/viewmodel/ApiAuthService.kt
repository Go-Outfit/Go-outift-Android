package com.example.gooutfit_android.ui.auth.viewmodel

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAuthService {
    @POST("login")
    fun login(
        @Body authRequest: AuthRequest
    ): Call<AuthResponse>

    @POST("register")
    fun register(
        @Body authRegister: AuthRegister
    ): Call<RegisterResponse>
}