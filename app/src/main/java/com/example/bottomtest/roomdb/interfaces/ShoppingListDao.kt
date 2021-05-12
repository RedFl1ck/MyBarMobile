package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.ItemShopping
import com.example.bottomtest.roomdb.model.ShoppingList

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: ShoppingList)

    @Update
    suspend fun updateItem(item: ShoppingList)

    @Delete
    suspend fun deleteItem(item: ShoppingList)

    @Query("SELECT * FROM ingredients JOIN shopping_list ON ingredients.id = shopping_list.ingredient_id ORDER BY id ASC")
    fun readAllData(): LiveData<List<ItemShopping>>

    @Query("UPDATE shopping_list SET number = :value, measuring = :volume WHERE ingredient_id = :id")
    fun setValue(value : Float, volume : String, id: Int)

    @Query("DELETE FROM shopping_list WHERE ingredient_id = :id")
    fun delete(id: Int)
}