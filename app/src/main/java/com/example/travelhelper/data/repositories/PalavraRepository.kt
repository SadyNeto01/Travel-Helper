package com.example.travelhelper.data.repositories

import com.example.travelhelper.data.db.PalavraDao
import com.example.travelhelper.data.models.PalavrasEIdiomas
import java.util.Locale

class PalavraRepository(private val palavraDao: PalavraDao) {

    fun listarPalavras(idiomaDoTelefone: String): List<PalavrasEIdiomas> {
        return palavraDao.obterPalavras(idiomaDoTelefone)
    }
    fun buscarPalavra(termo: String,idiomaDoTelefone: String): List<PalavrasEIdiomas> {
        return palavraDao.buscarPalavraNoIdioma(termo, idiomaDoTelefone)
    }

}

