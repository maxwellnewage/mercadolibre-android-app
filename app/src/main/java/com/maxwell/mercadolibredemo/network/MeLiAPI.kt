package com.maxwell.mercadolibredemo.network

import com.maxwell.mercadolibredemo.network.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MeLiAPI {
    @GET("search")
    fun search(@Query("q") term: String) : Call<SearchResponse>
}