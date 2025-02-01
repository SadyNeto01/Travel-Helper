package com.example.travelhelper.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "language_helper.db"
        private const val DATABASE_VERSION = 2 // Aumentei a versão para forçar a recriação
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d("DatabaseHelper", "Criando a tabela palavras_e_idiomas")
        db.execSQL(
            """
            CREATE TABLE palavras_e_idiomas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                pt TEXT NOT NULL,
                en TEXT NOT NULL,
                es TEXT NOT NULL,
                fr TEXT NOT NULL
            )
            """
        )

        seedDatabase(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("DatabaseHelper", "Atualizando banco de dados da versão $oldVersion para $newVersion")
        db.execSQL("DROP TABLE IF EXISTS palavras_e_idiomas")
        onCreate(db)
    }

    fun resetDatabase() {
        writableDatabase.use { db ->
            db.execSQL("DROP TABLE IF EXISTS palavras_e_idiomas")
            onCreate(db)
        }
    }

    private fun seedDatabase(db: SQLiteDatabase) {
        Log.d("DatabaseHelper", "Inserindo dados iniciais no banco")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Casa', 'House', 'Casa', 'Maison')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Carro', 'Car', 'Coche', 'Voiture')")
        db.execSQL("INSERT INTO palavras_e_idiomas (pt, en, es, fr) VALUES ('Livro', 'Book', 'Libro', 'Livre')")
    }
}