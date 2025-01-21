package com.example.travelhelper

import android.app.Application
import com.example.travelhelper.data.db.DatabaseHelper

class TravelHelperApp : Application() {

    companion object {
        lateinit var databaseHelper: DatabaseHelper
            private set
    }

    override fun onCreate() {
        super.onCreate()

        databaseHelper = DatabaseHelper(this)
    }
}
