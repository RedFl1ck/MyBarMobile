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
    fun setUnFavourite(id: Int)

    @Query("INSERT INTO shopping_list (ingredient_id, number, measuring) values(:id, 0, 'шт.')")
    fun addToCart(id: Int)

    @Query("SELECT 1 FROM shopping_list WHERE ingredient_id = :id")
    fun isInCart(id: Int): Boolean

    @Query("SELECT ingredient_id FROM shopping_list")
    fun readShoppingList(): LiveData<List<Int>>

    @Query("SELECT cocktails.id, name, description, degree, picture, cocktails.volume, receipt, cocktail_group, basis_id, taste, is_deleted, is_updatable, is_favourite FROM cocktails JOIN cocktails_ingredients ON cocktails_ingredients.cocktail_id = cocktails.id WHERE ingredient_id = :id ORDER BY cocktails.id ASC")
    fun getCocktailsByIngredientId(id: Int): LiveData<List<Cocktail>>

    @Query("SELECT * FROM ingredients where name LIKE :searchQuery ORDER BY id ASC")
    fun searchIngredients(searchQuery: String): LiveData<List<Ingredients>>
}