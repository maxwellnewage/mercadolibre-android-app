package com.maxwell.mercadolibredemo.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Address(
    private val state_name: String,
    private val city_name: String): Parcelable {

    override fun toString(): String {
        return "$state_name, $city_name"
    }
}