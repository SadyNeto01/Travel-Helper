package com.example.travelhelper.data.repositories

import com.example.travelhelper.data.db.PalavraDao
import com.example.travelhelper.data.models.PalavrasEIdiomas
import java.util.Locale

class PalavraRepository(private val palavraDao: PalavraDao) {

    fun listarPalavras(): List<PalavrasEIdiomas> {
        val idiomaDoTelefone = Locale.getDefault().language
        return palavraDao.obterPalavras(idiomaDoTelefone)
    }
}

