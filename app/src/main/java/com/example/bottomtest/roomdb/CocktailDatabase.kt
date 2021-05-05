package com.example.bottomtest.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomtest.roomdb.interfaces.CocktailDao
import com.example.bottomtest.roomdb.model.*
import java.io.File

@Database(entities = [Cocktail::class,
                     CocktailsIngredients::class,
                     CocktailsTools::class,
                     Ingredients::class,
                     IngredientsInStock::class,
                     ShoppingList::class,
                     Tools::class],
        version = 1, exportSchema = true)
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
                )
                        //.createFromAsset("cocktails.sql")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}