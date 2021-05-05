package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.roomdb.CocktailDatabase
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.repository.CocktailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Cocktail>>
    private val repository: CocktailRepository

    init {
        val cocktailDao = CocktailDatabase.getDatabase(
            application
        ).cocktailDao()
        repository = CocktailRepository(cocktailDao)
        readAllData = repository.readAllData
    }

    fun addCocktail(cocktail: Cocktail){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCocktail(cocktail)
        }
    }

    fun updateCocktail(cocktail: Cocktail){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCocktail(cocktail)
        }
    }

    fun deleteCocktail(cocktail: Cocktail){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCocktail(cocktail)
        }
    }
}