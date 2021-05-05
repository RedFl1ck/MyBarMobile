package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cocktails",
        foreignKeys = [ForeignKey(
                entity = Ingredients::class,
                parentColumns = ["id"],
                childColumns = ["basis_id"],
                onDelete = NO_ACTION,
                onUpdate = NO_ACTION)])
data class Cocktail(
        @PrimaryKey(autoGenerate = true)
    val id: Int,
        val name: String,
        val description: String,
        val degree: Int,
        val volume: Int,
        val receipt: String,
        val group: String,
        val basis_id: Int,
        val taste: String,
        val is_updatable: Boolean,
        var is_deleted: Boolean,
        val is_favourite: Boolean
): Parcelable