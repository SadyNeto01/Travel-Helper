package com.example.travelhelper.data.repositories

import com.example.travelhelper.data.db.TraducaoDao
import com.example.travelhelper.data.models.Traducao

class TraducaoRepository(private val traducaoDao: TraducaoDao) {

    fun adicionarTraducao(palavraId: Int, idiomaId: Int, traducao: String): Boolean {
        return traducaoDao.inserirTraducao(palavraId, idiomaId, traducao) > 0
    }

    fun atualizarTraducao(id: Int, novaTraducao: String): Boolean {
        return traducaoDao.atualizarTraducao(id, novaTraducao) > 0
    }

    fun removerTraducao(id: Int): Boolean {
        return traducaoDao.deletarTraducao(id) > 0
    }

    fun listarTraducoes(): List<Traducao> {
        return traducaoDao.obterTraducoes()
    }

    fun listarTraducoesPorPalavra(palavraId: Int): List<Traducao> {
        return traducaoDao.obterTraducoesPorPalavra(palavraId)
    }
}
