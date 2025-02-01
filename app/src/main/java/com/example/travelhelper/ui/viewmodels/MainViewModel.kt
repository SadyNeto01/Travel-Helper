package com.example.travelhelper.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.data.models.PalavrasEIdiomas
import com.example.travelhelper.data.repositories.PalavraRepository
import kotlinx.coroutines.launch
import java.util.Locale

class MainViewModel(
    private val palavraRepository: PalavraRepository
) : ViewModel() {

    private val _palavras = MutableLiveData<List<PalavrasEIdiomas>>()
    val palavras: LiveData<List<PalavrasEIdiomas>> get() = _palavras
    private var idiomaDoTelefone: String = Locale.getDefault().language
    init {
        carregarPalavras()
    }
    fun carregarPalavras() {
        viewModelScope.launch {
            val palavras = palavraRepository.listarPalavras(idiomaDoTelefone)
            _palavras.postValue(palavras)
        }
    }

    fun buscarPalavra(termo: String) {
        viewModelScope.launch {
            if (termo.isBlank()) {
                carregarPalavras()
            } else {
                val palavras = palavraRepository.buscarPalavra(termo,idiomaDoTelefone)
                _palavras.postValue(palavras)
            }
        }
    }
}
