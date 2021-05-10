package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomtest.roomdb.model.*

@Dao
interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredient(ingredient: Ingredients)

    @Update
    suspend fun updateIngredient(ingredient: Ingredients)

    @Delete
    suspend fun deleteIngredient(ingredients: Ingredients)

    @Query("SELECT * FROM ingredients ORDER BY id ASC")
    fun readAllData(): LiveData<List<Ingredients>>

    @Query("UPDATE ingredients SET is_favourite = 1 WHERE id = :id")
    fun setFavourite(id: Int)

    @Query("UPDATE ingredients SET is_favourite = 0 WHERE id = :id")
    fun setUnfavourite(id: Int)
}