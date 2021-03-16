package com.maxwell.mercadolibredemo.network.models

data class Transactions(
    val total: Int,
    val canceled: Int,
    val period: String,
    val ratings: Ratings,
    val completed: Int
)