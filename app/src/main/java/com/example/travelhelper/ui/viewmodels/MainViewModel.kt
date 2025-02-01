package com.example.travelhelper.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.data.models.PalavrasEIdiomas
import com.example.travelhelper.data.repositories.PalavraRepository
import kotlinx.coroutines.launch


class MainViewModel(
    private val palavraRepository: PalavraRepository
) : ViewModel() {

    private val _palavras = MutableLiveData<List<PalavrasEIdiomas>>()
    val palavras: LiveData<List<PalavrasEIdiomas>> get() = _palavras

    fun carregarPalavras() {
        viewModelScope.launch {
            val palavras = palavraRepository.listarPalavras()
            _palavras.postValue(palavras)
        }
    }
}