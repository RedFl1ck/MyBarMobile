package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.IngredientDao
import com.example.bottomtest.roomdb.model.Ingredients

class IngredientRepository(private val ingredientDao: IngredientDao) {

    val readAllData: LiveData<List<Ingredients>> = ingredientDao.readAllData()

    suspend fun addCocktail(ingredient: Ingredients){
        ingredientDao.addIngredient(ingredient)
    }

    suspend fun updateCocktail(ingredient: Ingredients){
        ingredientDao.updateIngredient(ingredient)
    }

    suspend fun deleteCocktail(ingredient: Ingredients){
        ingredientDao.deleteIngredient(ingredient)
    }
}