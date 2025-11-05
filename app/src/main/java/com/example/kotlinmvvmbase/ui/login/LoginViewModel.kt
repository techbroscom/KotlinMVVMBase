package com.example.kotlinmvvmbase.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        // Simple mock login check
        val isValid = username == "test" && password == "test"
        _loginResult.value = isValid
    }
}