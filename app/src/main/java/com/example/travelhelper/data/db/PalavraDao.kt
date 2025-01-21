package com.example.travelhelper.data.db

import android.content.ContentValues
import android.database.Cursor
import com.example.travelhelper.data.models.Palavra

class PalavraDao(private val dbHelper: DatabaseHelper) {

    fun inserirPalavra(palavraBase: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("palavra_base", palavraBase)
        }
        return db.insert("palavras", null, values)
    }

    fun atualizarPalavra(id: Int, palavraBase: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("palavra_base", palavraBase)
        }
        return db.update("palavras", values, "id=?", arrayOf(id.toString()))
    }

    fun deletarPalavra(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("palavras", "id=?", arrayOf(id.toString()))
    }

    fun obterPalavras(): List<Palavra> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("palavras", arrayOf("id", "palavra_base"), null, null, null, null, null)
        val palavras = mutableListOf<Palavra>()

        while (cursor.moveToNext()) {
            val palavra = Palavra(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                palavraBase = cursor.getString(cursor.getColumnIndexOrThrow("palavra_base"))
            )
            palavras.add(palavra)
        }
        cursor.close()
        return palavras
    }
}
