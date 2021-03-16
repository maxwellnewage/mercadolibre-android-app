package com.maxwell.mercadolibredemo.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellerReputation(
    val transactions: Transactions,
): Parcelable