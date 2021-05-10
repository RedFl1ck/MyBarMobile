package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.FavouriteDao
import com.example.bottomtest.roomdb.model.Cocktail

class FavouriteRepository(private val favouriteDao: FavouriteDao) {
    val readCocktailsData: LiveData<List<Cocktail>> = favouriteDao.readCocktailsData()
}