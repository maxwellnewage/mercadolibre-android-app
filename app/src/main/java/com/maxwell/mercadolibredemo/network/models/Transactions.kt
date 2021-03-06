package com.maxwell.mercadolibredemo.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Transactions(
    val total: Int,
    val canceled: Int,
    val period: String,
    val ratings: Ratings,
    val completed: Int
): Parcelable