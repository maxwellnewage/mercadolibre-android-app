package com.maxwell.mercadolibredemo

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxwell.mercadolibredemo.network.MeLiBuilder
import com.maxwell.mercadolibredemo.network.models.Product
import com.maxwell.mercadolibredemo.network.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

//val EMPTY_PRODUCTS_LIST: List<Product> = Collections.emptyList()

class SearchViewModel(val onSearchResponse: OnSearchResponse): ViewModel() {
    val products = MutableLiveData<List<Product>>()

//    init {
//        products.postValue(EMPTY_PRODUCTS_LIST)
//    }

    fun search(term: String) {
        MeLiBuilder.api.search(term).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful && response.body() != null) {
                    products.postValue(response.body()!!.results)
                    onSearchResponse.onSearchSuccess()
                } else {
                    onSearchResponse.onSearchError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                onSearchResponse.onSearchError(t.message)
            }
        })
    }

    interface OnSearchResponse {
        fun onSearchSuccess()
        fun onSearchError(message: String?)
    }
}