package com.example.bottomtest.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomtest.roomdb.interfaces.CocktailDao
import com.example.bottomtest.roomdb.model.Cocktail

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(context: Context): CocktailDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocktailDatabase::class.java,
                    "cocktail_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}