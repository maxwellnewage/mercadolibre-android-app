package com.maxwell.mercadolibredemo.network.search

import com.maxwell.mercadolibredemo.network.MeLiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchTest {
    private const val SEARCH_TERM_LENGTH = 3

    fun validateTerm(term: String?): Boolean {
        if(term.isNullOrEmpty()) return false

        return term.length > SEARCH_TERM_LENGTH // the term has to be > 3 for search a product
    }

    fun validateResponseCode(term: String): Boolean {
        return MeLiBuilder.api.search(term).execute().code() == 200
    }

    fun validateProducts(term: String): Boolean {
        val response: Response<SearchResponse> = MeLiBuilder.api.search(term).execute()

        // always the body is not null, so it's acceptable the use of bang bang operator
        if(response.body()!!.results.isNullOrEmpty()) return false

        return true
    }
}