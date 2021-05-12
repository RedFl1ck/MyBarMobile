package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.roomdb.CocktailDatabase
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.repository.IngredientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngredientViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Ingredients>>
    private val repository: IngredientRepository

    init {
        val ingredientDao = CocktailDatabase.getDatabase(
            application
        ).ingredientDao()
        repository = IngredientRepository(ingredientDao)//IngredientRepository(ingredientDao)
        readAllData = repository.readAllData
    }


    fun addIngredient(ingredient: Ingredients){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIngredient(ingredient)
        }
    }

    fun updateIngredient(ingredient: Ingredients){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIngredient(ingredient)
        }
    }

    fun deleteIngredient(ingredient: Ingredients){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIngredient(ingredient)
        }
    }

    fun setFavourite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavourite(id)
        }
    }

    fun setUnFavourite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUnFavourite(id)
        }
    }

    fun addToCart(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(id)
        }
    }
}