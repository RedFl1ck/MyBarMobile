package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import android.text.Editable
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

    val readAllData: LiveData<List<Cocktail>>
    private val repository: CocktailRepository

    init {
        val cocktailDao = CocktailDatabase.getDatabase(
            application
        ).cocktailDao()
        repository = CocktailRepository(cocktailDao)
        readAllData = repository.readAllData
    }

    fun inputCheck(name: String,
                   description: String,
                   degree: Editable,
                   volume: Editable,
                   receipt: String,
                   group: String,
                   basis_id: Editable,
                   taste: String,
                   is_updatable : Boolean,
                   is_deleted : Boolean,
                   is_favourite: Boolean): Boolean{
        return !(TextUtils.isEmpty(name)
                && TextUtils.isEmpty(description)
                && degree.isEmpty()
                && volume.isEmpty()
                && TextUtils.isEmpty(receipt)
                && TextUtils.isEmpty(group)
                && basis_id.isEmpty()
                && TextUtils.isEmpty(taste)
                && TextUtils.isEmpty(is_updatable.toString())
                && TextUtils.isEmpty(is_deleted.toString())
                && TextUtils.isEmpty(is_favourite.toString()))
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