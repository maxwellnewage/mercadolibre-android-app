package com.maxwell.mercadolibredemo.network.models

import java.util.*

data class Seller(
    val id: String,
    val permalink: String,
    val registration_date: Date,
    val car_dealer: Boolean,
    val real_estate_agency: Boolean,
    val tags: List<String>,
    val eshop: Eshop,
    val seller_reputation: SellerReputation
)