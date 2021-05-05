package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ingredients")
data class Ingredients(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    /**картинка в виде массива байтов*/
    val picture: ByteArray,
    val degree: Int,
    val is_favourite: Boolean
): Parcelable