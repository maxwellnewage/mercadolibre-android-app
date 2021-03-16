package com.maxwell.mercadolibredemo.network.models

data class Eshop(
    val nick_name: String,
//    val eshop_rubro: String,
    val eshop_id: Long,
    val eshop_logo_url: String,
    val seller: Long,
    val eshop_experience: Long
)