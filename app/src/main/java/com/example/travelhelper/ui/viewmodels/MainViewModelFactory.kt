package com.example.travelhelper.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelhelper.data.repositories.PalavraRepository

class MainViewModelFactory(private val palavraRepository: PalavraRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(palavraRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
