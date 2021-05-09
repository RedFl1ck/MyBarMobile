package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.roomdb.CocktailDatabase
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.repository.FavouriteRepository
import com.example.bottomtest.roomdb.repository.IngredientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application): AndroidViewModel(application)  {

    val readAllData: LiveData<List<Cocktail>>
    private val repository: FavouriteRepository

    init {
        val favouriteDao = CocktailDatabase.getDatabase(
            application
        ).favouriteDao()
        repository = FavouriteRepository(favouriteDao)
        readAllData = repository.readAllData
    }
}