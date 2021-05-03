package com.maxwell.mercadolibredemo.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Attributes(
    val name: String,
    val value_name: String?,
): Parcelable