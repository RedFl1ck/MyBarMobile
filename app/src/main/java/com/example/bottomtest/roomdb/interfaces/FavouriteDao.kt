package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.bottomtest.roomdb.model.Cocktail

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM cocktails WHERE is_favourite = 1 ORDER BY id ASC")
    fun readCocktailsData(): LiveData<List<Cocktail>>
}