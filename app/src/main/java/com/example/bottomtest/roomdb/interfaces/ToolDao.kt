package com.example.bottomtest.roomdb.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomtest.roomdb.model.Tools

@Dao
interface ToolDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTool(tools: Tools)

    @Update
    suspend fun updateTool(tools: Tools)

    @Delete
    suspend fun deleteTool(tools: Tools)

    @Query("SELECT * FROM tools ORDER BY id ASC")
    fun readAllData(): LiveData<List<Tools>>
}