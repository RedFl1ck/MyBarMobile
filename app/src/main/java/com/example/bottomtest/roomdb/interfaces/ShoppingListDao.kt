package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomtest.roomdb.model.Ingredients

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(ingredient: Ingredients)

    @Update
    suspend fun updateItem(ingredient: Ingredients)

    @Delete
    suspend fun deleteItem(ingredients: Ingredients)

    @Query("SELECT * FROM ingredients JOIN shopping_list ON ingredients.id = shopping_list.ingredient_id ORDER BY id ASC")
    fun readAllData(): LiveData<List<Ingredients>>
}