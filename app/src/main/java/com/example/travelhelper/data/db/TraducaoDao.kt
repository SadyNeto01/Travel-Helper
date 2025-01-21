package com.example.travelhelper.data.db

import android.content.ContentValues
import android.database.Cursor
import com.example.travelhelper.data.models.Traducao

class TraducaoDao(private val dbHelper: DatabaseHelper) {

    fun inserirTraducao(palavraId: Int, idiomaId: Int, traducao: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("palavra_id", palavraId)
            put("idioma_id", idiomaId)
            put("traducao", traducao)
        }
        return db.insert("traducoes", null, values)
    }

    fun atualizarTraducao(id: Int, traducao: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("traducao", traducao)
        }
        return db.update("traducoes", values, "id=?", arrayOf(id.toString()))
    }

    fun deletarTraducao(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("traducoes", "id=?", arrayOf(id.toString()))
    }

    fun obterTraducoes(): List<Traducao> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "traducoes",
            arrayOf("id", "palavra_id", "idioma_id", "traducao"),
            null,
            null,
            null,
            null,
            null
        )
        val traducoes = mutableListOf<Traducao>()

        while (cursor.moveToNext()) {
            val traducao = Traducao(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                palavraId = cursor.getInt(cursor.getColumnIndexOrThrow("palavra_id")),
                idiomaId = cursor.getInt(cursor.getColumnIndexOrThrow("idioma_id")),
                traducao = cursor.getString(cursor.getColumnIndexOrThrow("traducao"))
            )
            traducoes.add(traducao)
        }
        cursor.close()
        return traducoes
    }

    fun obterTraducoesPorPalavra(palavraId: Int): List<Traducao> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "traducoes",
            arrayOf("id", "palavra_id", "idioma_id", "traducao"),
            "palavra_id=?",
            arrayOf(palavraId.toString()),
            null,
            null,
            null
        )
        val traducoes = mutableListOf<Traducao>()

        while (cursor.moveToNext()) {
            val traducao = Traducao(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                palavraId = cursor.getInt(cursor.getColumnIndexOrThrow("palavra_id")),
                idiomaId = cursor.getInt(cursor.getColumnIndexOrThrow("idioma_id")),
                traducao = cursor.getString(cursor.getColumnIndexOrThrow("traducao"))
            )
            traducoes.add(traducao)
        }
        cursor.close()
        return traducoes
    }
}
