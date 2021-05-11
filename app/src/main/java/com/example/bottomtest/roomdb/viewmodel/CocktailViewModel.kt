package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.roomdb.CocktailDatabase
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.repository.CocktailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailViewModel(application: Application): AndroidViewModel(application) {

    val readNotDeletedData: LiveData<List<Cocktail>>
    val readDeletedData: LiveData<List<Cocktail>>
    private val repository: CocktailRepository

    init {
        val cocktailDao = CocktailDatabase.getDatabase(
            application
        ).cocktailDao()
        repository = CocktailRepository(cocktailDao)
        readNotDeletedData = repository.readNotDeletedData
        readDeletedData = repository.readDeletedData
    }


    fun inputCheck(cocktail: Cocktail): Boolean{
        return !(TextUtils.isEmpty(cocktail.name)
                && TextUtils.isEmpty(cocktail.description)
                //&& degree.isEmpty()
                && TextUtils.isEmpty(cocktail.picture)
                //&& volume.isEmpty()
                && TextUtils.isEmpty(cocktail.receipt)
                && TextUtils.isEmpty(cocktail.cocktail_group)
                //&& basis_id.isEmpty()
                && TextUtils.isEmpty(cocktail.taste)
                && TextUtils.isEmpty(cocktail.is_updatable.toString())
                && TextUtils.isEmpty(cocktail.is_deleted.toString())
                && TextUtils.isEmpty(cocktail.is_favourite.toString()))
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

    fun getCocktailBasis(basis_id: Int): LiveData<String> {
        return repository.getCocktailBasis(basis_id)
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
}