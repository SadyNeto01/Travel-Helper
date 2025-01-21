package com.example.travelhelper.data.repositories

import com.example.travelhelper.data.db.IdiomaDao
import com.example.travelhelper.data.models.Idioma

class IdiomaRepository(private val idiomaDao: IdiomaDao) {

    fun adicionarIdioma(codigo: String, nome: String): Boolean {
        return idiomaDao.inserirIdioma(codigo, nome) > 0
    }

    fun atualizarIdioma(id: Int, novoNome: String): Boolean {
        return idiomaDao.atualizarIdioma(id, novoNome) > 0
    }

    fun removerIdioma(id: Int): Boolean {
        return idiomaDao.deletarIdioma(id) > 0
    }

    fun listarIdiomas(): List<Idioma> {
        return idiomaDao.obterIdiomas()
    }

    fun buscarIdiomaPorCodigo(codigo: String): Idioma? {
        return listarIdiomas().find { it.codigo == codigo }
    }
}
