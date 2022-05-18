package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.MyBarApplication
import com.example.bottomtest.roomdb.adapter.IngredientsListAdapter
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.IngredientsList
import com.example.bottomtest.roomdb.repository.IngredientRepository
import kotlinx.coroutines.*

class IngredientViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Ingredients>>
    val readShoppingList: LiveData<List<Int>>
    private val repository: IngredientRepository

    init {
        val ingredientDao = MyBarApplication.getDB().ingredientDao()
        repository = IngredientRepository(ingredientDao)
        readAllData = repository.readAllData
        readShoppingList = repository.readShoppingList
    }

    fun search(query: String, viewLifecycleOwner: LifecycleOwner, adapter: IngredientsListAdapter) {
        val searchQuery = "%$query%"

        searchIngredients(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }

    fun addIngredient(ingredient: Ingredients) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIngredient(ingredient)
        }
    }

    fun updateIngredient(ingredient: Ingredients) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIngredient(ingredient)
        }
    }

    fun deleteIngredient(ingredient: Ingredients) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIngredient(ingredient)
        }
    }

    fun setFavourite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavourite(id)
        }
    }

    fun getCocktailsByIngredientId(id: Int): LiveData<List<Cocktail>> {
        val selectedCocktails: LiveData<List<Cocktail>>
        viewModelScope.run {
            val job = async { repository.getCocktailsByIngredientId(id) }
            runBlocking {
                selectedCocktails = job.await()
            }
        }
        return selectedCocktails
    }

    fun setUnFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUnFavourite(id)
        }
    }

    fun isInCart(id: Int): Boolean {
        var isInCard: Boolean
        viewModelScope.run {
            val job = async { repository.isInCart(id) }
            runBlocking {
                isInCard = job.await()
            }
        }
        return isInCard
    }

    fun addToCart(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!repository.isInCart(id)) {
                repository.addToCart(id)
            }
        }
    }

    private fun searchIngredients(searchQuery: String): LiveData<List<Ingredients>> {
        val selectedIngredients: LiveData<List<Ingredients>>
        viewModelScope.run {
            val job = async { repository.searchIngredients(searchQuery) }
            runBlocking {
                selectedIngredients = job.await()
            }
        }
        return selectedIngredients
    }
}