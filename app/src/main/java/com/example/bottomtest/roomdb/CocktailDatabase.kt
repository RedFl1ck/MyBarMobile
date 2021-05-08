package com.example.bottomtest.roomdb

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bottomtest.roomdb.interfaces.CocktailDao
import com.example.bottomtest.roomdb.interfaces.IngredientDao
import com.example.bottomtest.roomdb.model.*
import java.io.BufferedReader
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
    abstract fun ingredientDao(): IngredientDao

    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(context: Context): CocktailDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): CocktailDatabase{
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
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                fillIngredients(context, db)
                                fillTools(context, db)
                                connectIngredientsAndCocktails(context, db)
                                connectToolsAndCocktails(context, db)
                                fillCocktails(context, db)
                            }
                        })
                        .build()
                INSTANCE = instance
                return instance
            }
        }

        private fun fillIngredients(context: Context, db: SupportSQLiteDatabase) {
            var br: BufferedReader? = null
            try {
                br = context.assets.open("ingredients.sql").bufferedReader()
                var line: String? = null
                while (br.readLine().also { line = it } != null) {
                    db.execSQL(line)
                }
            }
            catch (e: Exception){
            }
            finally {
                if (br != null) {
                    try {
                        br.close()
                    } catch (e: Exception) {
                    }
                }
            }
        }

        private fun fillTools(context: Context, db: SupportSQLiteDatabase) {
            var br: BufferedReader? = null
            try {
                br = context.assets.open("tools.sql").bufferedReader()
                var line: String? = null
                while (br.readLine().also { line = it } != null) {
                    db.execSQL(line)
                }
            }
            catch (e: Exception){
            }
            finally {
                if (br != null) {
                    try {
                        br.close()
                    } catch (e: Exception) {
                    }
                }
            }
        }

        private fun connectIngredientsAndCocktails(context: Context, db: SupportSQLiteDatabase) {
            var br: BufferedReader? = null
            try {
                br = context.assets.open("cocktails_ingredients.sql").bufferedReader()
                var line: String? = null
                while (br.readLine().also { line = it } != null) {
                    db.execSQL(line)
                }
            }
            catch (e: Exception){
            }
            finally {
                if (br != null) {
                    try {
                        br.close()
                    } catch (e: Exception) {
                    }
                }
            }
        }

        private fun connectToolsAndCocktails(context: Context, db: SupportSQLiteDatabase) {
            var br: BufferedReader? = null
            try {
                br = context.assets.open("cocktails_tools.sql").bufferedReader()
                var line: String? = null
                while (br.readLine().also { line = it } != null) {
                    db.execSQL(line)
                }
            }
            catch (e: Exception){
            }
            finally {
                if (br != null) {
                    try {
                        br.close()
                    } catch (e: Exception) {
                    }
                }
            }
        }

        private fun fillCocktails(context: Context, db: SupportSQLiteDatabase) {
            var br: BufferedReader? = null
            try {
                br = context.assets.open("cocktails.sql").bufferedReader()
                var line: String? = null
                while (br.readLine().also { line = it } != null) {
                    db.execSQL(line)
                }
            }
            catch (e: Exception){
            }
            finally {
                if (br != null) {
                    try {
                        br.close()
                    } catch (e: Exception) {
                    }
                }
           }
        }
        }
    }