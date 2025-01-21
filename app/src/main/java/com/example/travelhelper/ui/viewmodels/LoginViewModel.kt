package com.example.travelhelper.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun autenticarUsuario(username: String, password: String) {
        if (username == "admin" && password == "1234") {
            _loginResult.value = true
        } else {
            _loginResult.value = false
        }
    }
}
