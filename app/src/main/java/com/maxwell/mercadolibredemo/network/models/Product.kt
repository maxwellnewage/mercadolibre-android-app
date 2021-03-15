package com.maxwell.mercadolibredemo.network.models

class Product(
    val id: String,
    val site_id: String,
    val title: String,
    val seller: Seller,
    val price: Float,
    val currency_id: String,
    val available_quantity: Int,
    val sold_quantity: Int,
    val permalink: String,
    val thumbnail: String,
    val accepts_mercadopago: Boolean,
    val address: Address,
    val tags: List<String>
)