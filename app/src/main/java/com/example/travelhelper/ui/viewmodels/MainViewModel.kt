package com.example.travelhelper.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelhelper.data.models.Palavra
import com.example.travelhelper.data.repositories.PalavraRepository

class MainViewModel(private val palavraRepository: PalavraRepository) : ViewModel() {

    private val _palavras = MutableLiveData<List<Palavra>>()
    val palavras: LiveData<List<Palavra>> get() = _palavras

    fun carregarPalavras() {
        _palavras.value = palavraRepository.listarPalavras()
    }
}

