package com.example.travelhelper.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "language_helper.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE palavras (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                palavra_base TEXT NOT NULL UNIQUE
            )
            """
        )

        db.execSQL(
            """
            CREATE TABLE idiomas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                codigo TEXT NOT NULL UNIQUE,
                nome TEXT NOT NULL
            )
            """
        )

        db.execSQL(
            """
            CREATE TABLE traducoes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                palavra_id INTEGER NOT NULL,
                idioma_id INTEGER NOT NULL,
                traducao TEXT NOT NULL,
                FOREIGN KEY (palavra_id) REFERENCES palavras(id) ON DELETE CASCADE,
                FOREIGN KEY (idioma_id) REFERENCES idiomas(id) ON DELETE CASCADE,
                UNIQUE(palavra_id, idioma_id)
            )
            """
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS traducoes")
        db.execSQL("DROP TABLE IF EXISTS idiomas")
        db.execSQL("DROP TABLE IF EXISTS palavras")
        onCreate(db)
    }

    fun clearDatabase() {
        writableDatabase.use { db ->
            db.execSQL("DELETE FROM traducoes")
            db.execSQL("DELETE FROM idiomas")
            db.execSQL("DELETE FROM palavras")
        }
    }
}
