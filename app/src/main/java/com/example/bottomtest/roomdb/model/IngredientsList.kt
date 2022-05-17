package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// ToDo зачем она нужна? Уже есть готовая модель коктейля
@Parcelize
data class IngredientsList(
    val volume: Int,
    val id: Int,
    val name: String,
    val description: String,
    /**картинка в виде массива байтов*/
    val picture: ByteArray,
    val type: String?,
    val degree: Int,
    val is_favourite: Boolean,
    val created_by_user: Boolean,
    val open_count: Int?,
    val shopping_count: Int?
): Parcelable