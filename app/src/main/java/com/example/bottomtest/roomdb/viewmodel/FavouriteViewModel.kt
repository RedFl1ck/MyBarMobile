package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.bottomtest.MyBarApplication
import com.example.bottomtest.roomdb.adapter.CocktailListAdapter
import com.example.bottomtest.roomdb.adapter.FavouriteListAdapter
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.repository.FavouriteRepository

class FavouriteViewModel(application: Application): AndroidViewModel(application)  {

    val readCocktailsData: LiveData<List<Cocktail>>
    private val repository: FavouriteRepository

    init {
        val favouriteDao = MyBarApplication.getDB().favouriteDao()
        repository = FavouriteRepository(favouriteDao)
        readCocktailsData = repository.readCocktailsData
    }

    fun search(query: String, viewLifecycleOwner: LifecycleOwner, adapter: FavouriteListAdapter){
        val searchQuery = "%$query%"

        repository.searchCocktails(searchQuery).observe(viewLifecycleOwner, { list ->
            list.let {
                adapter.setData(it)
            }
        })
    }
}