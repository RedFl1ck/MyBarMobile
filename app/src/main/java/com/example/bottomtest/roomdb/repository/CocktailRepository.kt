package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.CocktailDao
import com.example.bottomtest.roomdb.model.*

class CocktailRepository(private val cocktailDao: CocktailDao) {

    val readNotDeletedData: LiveData<List<Cocktail>> = cocktailDao.readNotDeletedData()
    val readDeletedData: LiveData<List<Cocktail>> = cocktailDao.readDeletedData()

    suspend fun addCocktail(cocktail: Cocktail){
        cocktailDao.addCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: Cocktail){
        cocktailDao.updateCocktail(cocktail)
    }

    suspend fun deleteCocktail(cocktail: Cocktail){
        cocktailDao.deleteCocktail(cocktail)
    }
}