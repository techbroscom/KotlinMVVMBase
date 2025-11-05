package com.example.kotlinmvvmbase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvmbase.data.model.User
import com.example.kotlinmvvmbase.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun fetchUsers() {
        _loading.value = true

        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                _users.value = userList
            } catch (e: Exception) {
                _error.value = "Failed to load users: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}