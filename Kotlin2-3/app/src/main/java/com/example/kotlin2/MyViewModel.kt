package com.example.kotlin2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login(username: String, password: String) {
        // Простая проверка логина
        if (username == "admin" && password == "password") {
            _user.value = User(username, password)
            _loginSuccess.value = true
            Log.d("MyViewModel", "Login successful: Username=$username, Password=$password")
        } else {
            _loginSuccess.value = false
            Log.d("MyViewModel", "Login failed")
        }
    }
}



