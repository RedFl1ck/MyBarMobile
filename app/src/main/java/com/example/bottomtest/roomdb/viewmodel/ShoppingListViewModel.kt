package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.MyBarApplication
import com.example.bottomtest.roomdb.adapter.IngredientsListAdapter
import com.example.bottomtest.roomdb.adapter.ShoppingListAdapter
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.ItemShopping
import com.example.bottomtest.roomdb.model.ShoppingList
import com.example.bottomtest.roomdb.repository.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ShoppingListViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<ItemShopping>>
    private val repository: ShoppingListRepository

    init {
        val shoppingListDao = MyBarApplication.getDB().shoppingListDao()
        repository = ShoppingListRepository(shoppingListDao)//IngredientRepository(ingredientDao)
        readAllData = repository.readAllData
    }

    fun search(query: String, viewLifecycleOwner: LifecycleOwner, adapter: ShoppingListAdapter){
        val searchQuery = "%$query%"

        repository.searchIngredients(searchQuery).observe(viewLifecycleOwner, { list ->
            list.let {
                adapter.setData(it)
            }
        })
    }

    fun searchItems(searchQuery: String): LiveData<List<ItemShopping>>{
        val selectedIngredients : LiveData<List<ItemShopping>>
        viewModelScope.run {
            val job = async { repository.searchIngredients(searchQuery) }
            runBlocking {
                selectedIngredients =  job.await()
            }
        }
        return selectedIngredients
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