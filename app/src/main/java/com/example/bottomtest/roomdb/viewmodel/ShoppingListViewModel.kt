package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.roomdb.CocktailDatabase
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.repository.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Ingredients>>
    private val repository: ShoppingListRepository

    init {
        val shoppingListDao = CocktailDatabase.getDatabase(
            application
        ).shoppingListDao()
        repository = ShoppingListRepository(shoppingListDao)//IngredientRepository(ingredientDao)
        readAllData = repository.readAllData
    }


    fun addItem(ingredient: Ingredients){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(ingredient)
        }
    }

    fun updateItem(ingredient: Ingredients){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(ingredient)
        }
    }

    fun deleteItem(ingredient: Ingredients){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(ingredient)
        }
    }
}