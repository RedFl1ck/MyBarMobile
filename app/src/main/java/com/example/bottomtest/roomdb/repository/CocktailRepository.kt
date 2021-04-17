package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.CocktailDao
import com.example.bottomtest.roomdb.model.Cocktail

class CocktailRepository(private val cocktailDao: CocktailDao) {

    val readAllData: LiveData<List<Cocktail>> = cocktailDao.readAllData()

    suspend fun addCocktail(cocktail: Cocktail){
        cocktailDao.addCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: Cocktail){
        cocktailDao.updateCocktail(cocktail)
    }

    suspend fun deleteCocktail(cocktail: Cocktail){
        cocktailDao.deleteCocktail(cocktail)
    }

    suspend fun deleteAllCocktails(){
        cocktailDao.deleteAllCocktails()
    }

}