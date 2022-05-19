package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.IngredientDao
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.ShoppingChartItem

class IngredientRepository(private val dao: IngredientDao) {

    val readAllData: LiveData<List<Ingredients>> = dao.readAllData()
    val readShoppingList: LiveData<List<Int>> = dao.readShoppingList()

    suspend fun addIngredient(ingredient: Ingredients) {
        dao.addIngredient(ingredient)
    }

    suspend fun updateIngredient(ingredient: Ingredients) {
        dao.updateIngredient(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredients) {
        dao.deleteIngredient(ingredient)
    }

    fun setFavourite(id: Int) {
        dao.setFavourite(id)
    }

    fun setUnFavourite(id: Int) {
        dao.setUnFavourite(id)
    }

    fun addToCart(id: Int) {
        dao.addToCart(id)
    }

    fun getCocktailsByIngredientId(id: Int): LiveData<List<Cocktail>> {
        return dao.getCocktailsByIngredientId(id)
    }

    fun isInCart(id: Int): Boolean {
        return dao.isInCart(id)
    }

    fun searchIngredients(searchQuery: String): LiveData<List<Ingredients>> {
        return dao.searchIngredients(searchQuery)
    }

    fun incrementOpenCount(id: Int) {
        dao.incrementOpenCount(id)
    }

    suspend fun getDataForSoppingChart(limit: Int): List<ShoppingChartItem> {
        return dao.getDataForSoppingChart(limit)
    }
}