package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.ShoppingListDao
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.ItemShopping
import com.example.bottomtest.roomdb.model.ShoppingList

class ShoppingListRepository(private val shoppingListDao: ShoppingListDao) {

    val readAllData: LiveData<List<ItemShopping>> = shoppingListDao.readAllData()

    suspend fun addItem(item: ShoppingList){
        shoppingListDao.addItem(item)
    }

    suspend fun updateItem(item: ShoppingList){
        shoppingListDao.updateItem(item)
    }

    suspend fun deleteItem(item: ShoppingList){
        shoppingListDao.deleteItem(item)
    }

    fun setValue(value: Float, volume: String, id: Int){
        shoppingListDao.setValue(value, volume, id)
    }

    fun delete(id: Int){
        shoppingListDao.delete(id)
    }

    fun searchIngredients(searchQuery: String) : LiveData<List<ItemShopping>>{
        return shoppingListDao.searchItems(searchQuery)
    }

    fun updateShoppingCount(id: Int, amount: Int) {
        shoppingListDao.updateShoppingCount(id, amount)
    }
}