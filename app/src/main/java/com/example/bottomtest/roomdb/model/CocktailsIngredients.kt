package com.example.bottomtest.roomdb.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "cocktails_ingredients",
        foreignKeys = [
            ForeignKey(
                entity = Ingredients::class,
                parentColumns = ["id"],
                childColumns = ["ingredient_id"],
                onDelete = ForeignKey.NO_ACTION,
                onUpdate = ForeignKey.NO_ACTION),
            ForeignKey(
                    entity = Cocktail::class,
                    parentColumns = ["id"],
                    childColumns = ["cocktail_id"],
                    onDelete = ForeignKey.NO_ACTION,
                    onUpdate = ForeignKey.NO_ACTION),
        ],
    indices = [Index(value = ["cocktail_id"]), Index(value = ["ingredient_id"])])
data class CocktailsIngredients(
        @PrimaryKey(autoGenerate = true) var _id: Int = -1,
        val cocktail_id: Int,
        val ingredient_id: Int,
        val volume: Int
)