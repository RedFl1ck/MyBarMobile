package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.NO_ACTION
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cocktails",
        foreignKeys = [ForeignKey(
                entity = Ingredients::class,
                parentColumns = ["id"],
                childColumns = ["basis_id"],
                onDelete = NO_ACTION,
                onUpdate = NO_ACTION)],
        indices = [Index(value = ["id"])])
data class Cocktail @JvmOverloads constructor (
        @PrimaryKey(autoGenerate = true)
    val id: Int,
        val name: String,
        val description: String,
        val degree: Int,
        val picture: String,
        val volume: Int,
        val receipt: String,
        val cocktail_group: String?,
        val basis_id: Int,
        val taste: String,
        val is_updatable: Boolean,
        val is_deleted: Boolean,
        val is_favourite: Boolean,
        @Ignore val basis_name: String? = null
): Parcelable