package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.CocktailDao
import com.example.bottomtest.roomdb.model.*

class CocktailRepository(private val cocktailDao: CocktailDao) {

    val readNotDeletedData: LiveData<List<Cocktail>> = cocktailDao.readNotDeletedData()
    val readDeletedData: LiveData<List<Cocktail>> = cocktailDao.readDeletedData()

    suspend fun addCocktail(cocktail: Cocktail) {
        cocktailDao.addCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: Cocktail) {
        cocktailDao.updateCocktail(cocktail)
    }

    suspend fun deleteCocktail(cocktail: Cocktail) {
        cocktailDao.deleteCocktail(cocktail)
    }

    fun setFavourite(id: Int) {
        cocktailDao.setFavourite(id)
    }

    fun setUnFavourite(id: Int) {
        cocktailDao.setUnFavourite(id)
    }

    fun readSelectedCocktails(id: Int): LiveData<List<Cocktail>> {
        return cocktailDao.readSelectedCocktails(id)
    }

    fun searchCocktails(searchQuery: String): LiveData<List<Cocktail>> {
        return cocktailDao.searchCocktails(searchQuery)
    }

    fun incrementOpenCocktail(id: Int) {
        cocktailDao.incrementOpenCocktail(id)
    }

    fun getRecommendedCocktails(taste: String, id: Int): LiveData<List<Cocktail>> {
        return cocktailDao.getRecommendedCocktails(taste, id)
    }

    fun getCocktailBasis(basis_id: Int): LiveData<String> {
        return cocktailDao.getCocktailBasis(basis_id)
    }
}