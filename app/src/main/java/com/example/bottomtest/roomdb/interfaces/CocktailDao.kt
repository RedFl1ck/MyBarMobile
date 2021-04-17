package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomtest.roomdb.model.Cocktail

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCocktail(cocktail: Cocktail)

    @Update
    suspend fun updateCocktail(cocktail: Cocktail)

    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)

    @Query("DELETE FROM cocktail_table")
    suspend fun deleteAllCocktails()

    @Query("SELECT * FROM cocktail_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cocktail>>

}