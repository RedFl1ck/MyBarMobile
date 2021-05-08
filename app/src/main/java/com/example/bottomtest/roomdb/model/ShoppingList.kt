package com.example.bottomtest.roomdb.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list",
        foreignKeys = [
            ForeignKey(
                    entity = Ingredients::class,
                    parentColumns = ["id"],
                    childColumns = ["ingredient_id"],
                    onDelete = ForeignKey.NO_ACTION,
                    onUpdate = ForeignKey.NO_ACTION),
        ], indices = [Index(value = ["ingredient_id"])])
data class ShoppingList(
        @PrimaryKey(autoGenerate = true) var _id: Int = -1,
        val ingredient_id: Int,
        val number: Int,
        val measuring: String
)