package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.MyBarApplication
import com.example.bottomtest.roomdb.model.ItemShopping
import com.example.bottomtest.roomdb.model.ShoppingList
import com.example.bottomtest.roomdb.repository.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<ItemShopping>>
    private val repository: ShoppingListRepository

    init {
        val shoppingListDao = MyBarApplication.getDB().shoppingListDao()
        repository = ShoppingListRepository(shoppingListDao)//IngredientRepository(ingredientDao)
        readAllData = repository.readAllData
    }


    fun addItem(item: ShoppingList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    fun updateItem(item: ShoppingList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item: ShoppingList){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

    fun setValue(value: Float, volume: String, id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setValue(value, volume, id)
        }
    }

    fun delete(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }
}