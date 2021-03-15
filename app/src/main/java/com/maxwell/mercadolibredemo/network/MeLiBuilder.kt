package com.maxwell.mercadolibredemo.network

import com.maxwell.mercadolibredemo.network.search.SearchResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object MeLiBuilder {
    // I don't know what's sites_id, so I'll use MLA as the example of MeLi API Documentation
    // https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas
    private const val BASE_URL = "https://api.mercadolibre.com/sites/MLA/"

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    // Retrofit Singleton: https://stackoverflow.com/questions/61729790/retrofit-singleton-in-kotlin
    // Why Lazy: https://stackoverflow.com/a/36623703/1655160
    val api: MeLiAPI by lazy { retrofit().create(MeLiAPI::class.java) }
}
