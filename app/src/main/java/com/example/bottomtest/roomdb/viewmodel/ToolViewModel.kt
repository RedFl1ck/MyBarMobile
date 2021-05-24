package com.example.bottomtest.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomtest.MyBarApplication
import com.example.bottomtest.roomdb.model.Tools
import com.example.bottomtest.roomdb.repository.ToolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToolViewModel(application: Application): AndroidViewModel(application)  {

    val readAllData: LiveData<List<Tools>>
    private val repository: ToolRepository

    init {
        val toolDao = MyBarApplication.getDB().toolDao()
        repository = ToolRepository(toolDao)
        readAllData = repository.readAllData
    }


    fun addTool(tool: Tools){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTool(tool)
        }
    }

    fun updateTool(tool: Tools){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTool(tool)
        }
    }

    fun deleteTool(tool: Tools){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTool(tool)
        }
    }
}