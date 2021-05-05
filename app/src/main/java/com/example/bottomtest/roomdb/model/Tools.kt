package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tools")
data class Tools(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    /**картинка в виде массива байтов*/
    val picture: ByteArray,
    val created_by_user: Boolean
): Parcelable