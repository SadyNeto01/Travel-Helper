package com.example.travelhelper.data.db

import android.content.ContentValues
import android.database.Cursor
import com.example.travelhelper.data.models.Idioma

class IdiomaDao(private val dbHelper: DatabaseHelper) {

    fun inserirIdioma(codigo: String, nome: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("codigo", codigo)
            put("nome", nome)
        }
        return db.insert("idiomas", null, values)
    }

    fun atualizarIdioma(id: Int, nome: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nome", nome)
        }
        return db.update("idiomas", values, "id=?", arrayOf(id.toString()))
    }

    fun deletarIdioma(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("idiomas", "id=?", arrayOf(id.toString()))
    }

    fun obterIdiomas(): List<Idioma> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("idiomas", arrayOf("id", "codigo", "nome"), null, null, null, null, null)
        val idiomas = mutableListOf<Idioma>()

        while (cursor.moveToNext()) {
            val idioma = Idioma(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            )
            idiomas.add(idioma)
        }
        cursor.close()
        return idiomas
    }
}
