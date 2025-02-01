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
            arrayOf("pt", colunaIdioma)
        } else {
            arrayOf("pt", "en")
        }
        val cursor = db.query(
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
                val palavraPt = it.getString(it.getColumnIndexOrThrow("pt"))
                val palavraTraduzida = if (colunaIdioma != null) {
                    it.getString(it.getColumnIndexOrThrow(colunaIdioma))
                } else {
                    it.getString(it.getColumnIndexOrThrow("en"))
                }

                listaPalavras.add(PalavrasEIdiomas(0, palavraPt, palavraTraduzida, "", ""))
            }
        }
        return listaPalavras
    }

    fun buscarPalavraNoIdioma(termo: String, idiomaDoTelefone: String): List<PalavrasEIdiomas> {
        val db = dbHelper.readableDatabase
        val colunasValidas = listOf("pt", "en", "es", "fr")
        val colunaIdioma = if (colunasValidas.contains(idiomaDoTelefone)) idiomaDoTelefone else "en"

        val cursor = db.rawQuery(
            "SELECT pt, $colunaIdioma FROM palavras_e_idiomas WHERE LOWER(pt) LIKE LOWER(?) OR LOWER($colunaIdioma) LIKE LOWER(?)",
            arrayOf("%$termo%", "%$termo%")
        )

        val listaPalavras = mutableListOf<PalavrasEIdiomas>()
        cursor.use {
            while (it.moveToNext()) {
                val palavraPt = it.getString(it.getColumnIndexOrThrow("pt"))
                val palavraTraduzida = it.getString(it.getColumnIndexOrThrow(colunaIdioma))
                listaPalavras.add(PalavrasEIdiomas(0, palavraPt, palavraTraduzida, "", ""))
            }
        }
        return listaPalavras
    }
}
