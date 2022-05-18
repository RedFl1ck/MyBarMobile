package com.example.bottomtest.roomdb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ShoppingChartItem (
    val id: Int,
    val name: String,
    val shopping_count: Int
): Parcelable