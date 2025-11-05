package com.example.kotlinmvvmbase.data.repository

import com.example.kotlinmvvmbase.data.api.RetrofitClient
import com.example.kotlinmvvmbase.data.model.User

class UserRepository {

    private val api = RetrofitClient.apiService

    suspend fun getUsers(): List<User> {
        return api.getUsers()
    }
}