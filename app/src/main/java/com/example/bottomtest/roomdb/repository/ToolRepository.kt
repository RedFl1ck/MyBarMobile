package com.example.bottomtest.roomdb.repository

import androidx.lifecycle.LiveData
import com.example.bottomtest.roomdb.interfaces.ToolDao
import com.example.bottomtest.roomdb.model.Tools

class ToolRepository (private val toolDao: ToolDao) {

    val readAllData: LiveData<List<Tools>> = toolDao.readAllData()

    suspend fun addTool(tool: Tools){
        toolDao.addTool(tool)
    }

    suspend fun updateTool(tool: Tools){
        toolDao.updateTool(tool)
    }

    suspend fun deleteTool(tool: Tools){
        toolDao.deleteTool(tool)
    }
}