package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ingredients_in_stock",
        foreignKeys = [
            ForeignKey(
                    entity = Ingredients::class,
                    parentColumns = ["id"],
                    childColumns = ["ingredient_id"],
                    onDelete = ForeignKey.NO_ACTION,
                    onUpdate = ForeignKey.NO_ACTION),
        ], indices = [Index(value = ["ingredient_id"])])
data class IngredientsInStock(
        @PrimaryKey(autoGenerate = true) var _id: Int = -1,
        val ingredient_id: Int,
        val number: Int,
        val measuring: String
)