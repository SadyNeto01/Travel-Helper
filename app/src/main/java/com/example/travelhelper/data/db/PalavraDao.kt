package com.example.travelhelper.data.db

import android.database.Cursor
import com.example.travelhelper.data.models.PalavrasEIdiomas

class PalavraDao(private val dbHelper: DatabaseHelper) {
    fun obterPalavras(idiomaDoTelefone: String): List<PalavrasEIdiomas> {
        val db = dbHelper.readableDatabase
        val colunasDisponiveis = listOf("pt", "en", "es", "fr")

        val colunaIdioma = if (colunasDisponiveis.contains(idiomaDoTelefone)) {
            idiomaDoTelefone
        } else {
            null
        }

        val columns = if (colunaIdioma != null) {
            arrayOf("id", "pt", colunaIdioma)
        } else {
            arrayOf("id", "pt", "en")
        }
        val cursor: Cursor = db.query(
            "palavras_e_idiomas",
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val listaPalavras = mutableListOf<PalavrasEIdiomas>()

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("id"))
                val palavraPt = it.getString(it.getColumnIndexOrThrow("pt"))
                val palavraTraduzida = if (colunaIdioma != null) {
                    it.getString(it.getColumnIndexOrThrow(colunaIdioma))
                } else {
                    it.getString(it.getColumnIndexOrThrow("en"))
                }

                listaPalavras.add(PalavrasEIdiomas(id, palavraPt, palavraTraduzida, "", ""))
            }
        }
        return listaPalavras
    }
}