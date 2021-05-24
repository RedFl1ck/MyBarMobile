package com.example.bottomtest

import android.app.Application
import com.example.bottomtest.roomdb.CocktailDatabase

class MyBarApplication: Application() {

    companion object {

        lateinit var currentDatabase : CocktailDatabase

        fun getDB(): CocktailDatabase {
            return currentDatabase
        }
    }

    override fun onCreate() {
        super.onCreate()
        currentDatabase = CocktailDatabase.getDatabase(applicationContext)
    }
}