package com.maxwell.mercadolibredemo.network.models

class Address(
    private val state_name: String,
    private val city_name: String) {

    override fun toString(): String {
        return "$state_name, $city_name"
    }
}