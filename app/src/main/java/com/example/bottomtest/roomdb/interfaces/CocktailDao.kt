package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomtest.roomdb.model.*

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCocktail(cocktail: Cocktail)

    @Update
    suspend fun updateCocktail(cocktail: Cocktail)

    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)

    @Query("SELECT * FROM cocktails ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails where is_deleted == 0 ORDER BY id ASC")
    fun readNotDeletedData(): LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails where is_deleted == 1 ORDER BY id ASC")
    fun readDeletedData(): LiveData<List<Cocktail>>
}