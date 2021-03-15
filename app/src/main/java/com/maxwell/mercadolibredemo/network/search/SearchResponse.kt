package com.maxwell.mercadolibredemo.network.search

import com.maxwell.mercadolibredemo.network.models.Product

data class SearchResponse(
    val results: List<Product>
)