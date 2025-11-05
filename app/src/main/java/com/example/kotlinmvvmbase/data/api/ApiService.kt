package com.example.kotlinmvvmbase.data.api

import com.example.kotlinmvvmbase.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}