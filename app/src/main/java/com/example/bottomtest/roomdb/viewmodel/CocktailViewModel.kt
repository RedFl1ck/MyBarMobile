package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.MyBarApplication
import com.example.bottomtest.roomdb.adapter.CocktailListAdapter
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.model.IngredientsList
import com.example.bottomtest.roomdb.repository.CocktailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CocktailViewModel(application: Application) : AndroidViewModel(application) {

    val readNotDeletedData: LiveData<List<Cocktail>>
    private val readDeletedData: LiveData<List<Cocktail>>
    private val repository: CocktailRepository

    init {
        val cocktailDao = MyBarApplication.getDB().cocktailDao()
        repository = CocktailRepository(cocktailDao)
        readNotDeletedData = repository.readNotDeletedData
        readDeletedData = repository.readDeletedData
    }

    fun search(query: String, viewLifecycleOwner: LifecycleOwner, adapter: CocktailListAdapter) {
        val searchQuery = "%$query%"

        searchCocktails(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }


    fun inputCheck(cocktail: Cocktail): Boolean {
        return !(TextUtils.isEmpty(cocktail.name)
                && TextUtils.isEmpty(cocktail.description)
                && cocktail.degree.toString().isEmpty()
                && TextUtils.isEmpty(cocktail.picture)
                && cocktail.volume.toString().isEmpty()
                && TextUtils.isEmpty(cocktail.receipt)
                && TextUtils.isEmpty(cocktail.cocktail_group)
                && cocktail.basis_id.toString().isEmpty()
                && TextUtils.isEmpty(cocktail.taste)
                && TextUtils.isEmpty(cocktail.is_updatable.toString())
                && TextUtils.isEmpty(cocktail.is_deleted.toString())
                && TextUtils.isEmpty(cocktail.is_favourite.toString()))
    }

    fun addCocktail(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCocktail(cocktail)
        }
    }

    fun updateCocktail(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCocktail(cocktail)
        }
    }

    fun deleteCocktail(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCocktail(cocktail)
        }
    }

    /*fun getCocktailBasis(basis_id: Int):  LiveData<String>{
        val basis : LiveData<String>
        viewModelScope.run {
            val job = async { repository.getCocktailBasis(basis_id) }
            runBlocking {
                basis =  job.await()
            }
        }
        return basis
    }*/

    fun setFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavourite(id)
        }
    }

    fun setUnFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUnFavourite(id)
        }
    }

    fun getIngredientsByCocktailId(id: Int): LiveData<List<IngredientsList>> {
        val selectedIngredients: LiveData<List<IngredientsList>>
        viewModelScope.run {
            val job = async { repository.getIngredientsByCocktailId(id) }
            runBlocking {
                selectedIngredients = job.await()
            }
        }
        return selectedIngredients
    }

    private fun searchCocktails(searchQuery: String): LiveData<List<Cocktail>> {
        val selectedCocktails: LiveData<List<Cocktail>>
        viewModelScope.run {
            val job = async { repository.searchCocktails(searchQuery) }
            runBlocking {
                selectedCocktails = job.await()
            }
        }
        return selectedCocktails
    }

    fun incrementOpenCocktail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.incrementOpenCocktail(id)
        }
    }

    fun getRecommendedCocktails(taste: String, id: Int): LiveData<List<Cocktail>> {
        val selectedCocktails: LiveData<List<Cocktail>>
        viewModelScope.run {
            val job = async { repository.getRecommendedCocktails(taste, id) }
            runBlocking {
                selectedCocktails = job.await()
            }
        }
        return selectedCocktails
    }

    fun getCocktailBasis(basis_id: Int): LiveData<String> {
        return repository.getCocktailBasis(basis_id)
    }
}