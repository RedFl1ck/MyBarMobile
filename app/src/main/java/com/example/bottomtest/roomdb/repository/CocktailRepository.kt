package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.CocktailDao
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.model.IngredientsList

class CocktailRepository(private val dao: CocktailDao) {

    val readNotDeletedData: LiveData<List<Cocktail>> = dao.readNotDeletedData()
    val readDeletedData: LiveData<List<Cocktail>> = dao.readDeletedData()

    suspend fun addCocktail(cocktail: Cocktail) {
        dao.addCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: Cocktail) {
        dao.updateCocktail(cocktail)
    }

    suspend fun deleteCocktail(cocktail: Cocktail) {
        dao.deleteCocktail(cocktail)
    }

    fun setFavourite(id: Int) {
        dao.setFavourite(id)
    }

    fun setUnFavourite(id: Int) {
        dao.setUnFavourite(id)
    }

    fun getIngredientsByCocktailId(id: Int): LiveData<List<IngredientsList>> {
        return dao.getIngredientsByCocktailId(id)
    }

    fun searchCocktails(searchQuery: String): LiveData<List<Cocktail>> {
        return dao.searchCocktails(searchQuery)
    }

    fun filterCocktails(strong: Boolean): LiveData<List<Cocktail>> {
        return dao.filterCocktails(strong)
    }

    fun incrementOpenCocktail(id: Int) {
        dao.incrementOpenCocktail(id)
    }

    fun getRecommendedCocktails(taste: String, id: Int): LiveData<List<Cocktail>> {
        return dao.getRecommendedCocktails(taste, id)
    }

    fun getCocktailBasis(basis_id: Int): LiveData<String> {
        return dao.getCocktailBasis(basis_id)
    }
}