package com.example.travelhelper.data.repositories

import com.example.travelhelper.data.db.PalavraDao
import com.example.travelhelper.data.models.Palavra

class PalavraRepository(private val palavraDao: PalavraDao) {

    fun adicionarPalavra(palavraBase: String): Boolean {
        return palavraDao.inserirPalavra(palavraBase) > 0
    }

    fun atualizarPalavra(id: Int, novaPalavra: String): Boolean {
        return palavraDao.atualizarPalavra(id, novaPalavra) > 0
    }

    fun removerPalavra(id: Int): Boolean {
        return palavraDao.deletarPalavra(id) > 0
    }

    fun listarPalavras(): List<Palavra> {
        return palavraDao.obterPalavras()
    }

    fun buscarPalavraPorId(id: Int): Palavra? {
        return listarPalavras().find { it.id == id }
    }
}
