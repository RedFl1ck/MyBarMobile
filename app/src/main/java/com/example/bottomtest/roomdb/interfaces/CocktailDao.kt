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

    @Query("SELECT * FROM cocktails where name LIKE :searchQuery ORDER BY id ASC")
    fun searchCocktails(searchQuery: String): LiveData<List<Cocktail>>

    @Query("UPDATE cocktails SET is_favourite = 1 WHERE id = :id")
    fun setFavourite(id: Int)

    @Query("UPDATE cocktails SET is_favourite = 0 WHERE id = :id")
    fun setUnFavourite(id: Int)

    @Query("SELECT cocktails.id, name, description, degree, picture, cocktails.volume, receipt, cocktail_group, basis_id, taste, is_deleted, is_updatable, is_favourite FROM cocktails JOIN cocktails_ingredients ON cocktails_ingredients.cocktail_id = cocktails.id WHERE ingredient_id = :id ORDER BY cocktails.id ASC")
    fun readSelectedCocktails(id: Int): LiveData<List<Cocktail>>

    @Query("UPDATE cocktails SET open_count = open_count + 1 WHERE id = :id")
    fun incrementOpenCocktail(id: Int)

    @Query("SELECT * FROM cocktails WHERE taste = :taste AND is_favourite = 0 AND id != :id ORDER BY open_count DESC LIMIT 5")
    fun getRecommendedCocktails(taste: String, id: Int): LiveData<List<Cocktail>>

    @Query("SELECT name FROM ingredients WHERE id = :basis_id")
    fun getCocktailBasis(basis_id: Int): LiveData<String>
}