package com.maxwell.mercadolibredemo.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxwell.mercadolibredemo.network.MeLiBuilder
import com.maxwell.mercadolibredemo.network.models.Product
import com.maxwell.mercadolibredemo.network.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    val products = MutableLiveData<List<Product>>()
    private var onSearchResponse: OnSearchResponse? = null

    fun search(term: String) {
        MeLiBuilder.api.search(term).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful && response.body() != null) {
                    products.postValue(response.body()!!.results)
                    onSearchResponse?.onSearchSuccess()
                } else {
                    onSearchResponse?.onSearchError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                onSearchResponse?.onSearchError(t.message)
            }
        })
    }

    fun setOnSearchResponse(onSearch: OnSearchResponse) {
        this.onSearchResponse = onSearch
    }

    interface OnSearchResponse {
        fun onSearchSuccess()
        fun onSearchError(message: String?)
    }
}