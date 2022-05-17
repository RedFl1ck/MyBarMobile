package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.IngredientDao
import com.example.bottomtest.roomdb.model.*

class IngredientRepository(private val ingredientDao: IngredientDao) {

    val readAllData: LiveData<List<Ingredients>> = ingredientDao.readAllData()
    val readShoppingList: LiveData<List<Int>> = ingredientDao.readShoppingList()

    suspend fun addIngredient(ingredient: Ingredients) {
        ingredientDao.addIngredient(ingredient)
    }

    suspend fun updateIngredient(ingredient: Ingredients) {
        ingredientDao.updateIngredient(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredients) {
        ingredientDao.deleteIngredient(ingredient)
    }

    fun setFavourite(id: Int){
        ingredientDao.setFavourite(id)
    }

    fun setUnFavourite(id: Int) {
        ingredientDao.setUnFavourite(id)
    }

    fun addToCart(id: Int) {
        ingredientDao.addToCart(id)
    }

    fun readSelectedIngredients(id: Int): LiveData<List<IngredientsList>>{
        return ingredientDao.readSelectedIngredients(id)
    }

    fun isInCart(id: Int): Boolean {
        return ingredientDao.isInCart(id)
    }

    fun searchIngredients(searchQuery: String): LiveData<List<Ingredients>> {
        return ingredientDao.searchIngredients(searchQuery)
    }
}