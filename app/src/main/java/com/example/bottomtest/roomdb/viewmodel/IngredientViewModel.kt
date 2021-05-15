package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import android.text.BoringLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.roomdb.CocktailDatabase
import com.example.bottomtest.roomdb.model.CocktailsIngredients
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.IngredientsList
import com.example.bottomtest.roomdb.model.ShoppingList
import com.example.bottomtest.roomdb.repository.IngredientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class IngredientViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Ingredients>>
    val readShoppingList: LiveData<List<Int>>
    private val repository: IngredientRepository
    var readSelectedIngredients: LiveData<List<CocktailsIngredients>>? = null

    init {
        val ingredientDao = CocktailDatabase.getDatabase(
            application
        ).ingredientDao()
        repository = IngredientRepository(ingredientDao)
        readAllData = repository.readAllData
        readShoppingList = repository.readShoppingList
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

    fun readSelectedIngredients(id: Int): LiveData<List<IngredientsList>>{
        val selectedIngredients : LiveData<List<IngredientsList>>
        viewModelScope.run {
            val job = async { repository.readSelectedIngredients(id) }
            runBlocking {
                selectedIngredients =  job.await()
            }
        }
        return selectedIngredients
    }

    fun setUnFavourite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUnFavourite(id)
        }
    }

    fun isInCart(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.isInCart(id)
        }
    }

    fun addToCart(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            if (!repository.isInCart(id)){
                repository.addToCart(id)
            }
        }
    }
}