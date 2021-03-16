package com.maxwell.mercadolibredemo.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ratings(
    val negative: Float,
    val positive: Float,
    val neutral: Float
): Parcelable