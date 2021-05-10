package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.ShoppingListDao
import com.example.bottomtest.roomdb.model.Ingredients

class ShoppingListRepository(private val shoppingListDao: ShoppingListDao) {

    val readAllData: LiveData<List<Ingredients>> = shoppingListDao.readAllData()

    suspend fun addItem(ingredient: Ingredients){
        shoppingListDao.addItem(ingredient)
    }

    suspend fun updateItem(ingredient: Ingredients){
        shoppingListDao.updateItem(ingredient)
    }

    suspend fun deleteItem(ingredient: Ingredients){
        shoppingListDao.deleteItem(ingredient)
    }
}