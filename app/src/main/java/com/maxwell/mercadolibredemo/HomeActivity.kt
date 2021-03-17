package com.maxwell.mercadolibredemo

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.mercadolibredemo.ui.search.SearchAdapter
import com.maxwell.mercadolibredemo.ui.search.SearchViewModel

class HomeActivity : AppCompatActivity(), SearchViewModel.OnSearchResponse {
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.setOnSearchResponse(this)

        val iEmptyList: View = findViewById(R.id.iEmptyList)
        val rvProducts: RecyclerView = findViewById(R.id.rvProducts)
        val adapter = SearchAdapter(this, emptyList())
        rvProducts.adapter = adapter
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.setHasFixedSize(true)

        val pbSearching: ProgressBar = findViewById(R.id.pbSearching)

        val etSearchProduct: EditText = findViewById(R.id.etSearchProduct)

        etSearchProduct.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if(etSearchProduct.text.length > 3){
                    iEmptyList.visibility = View.GONE
                    pbSearching.visibility = View.VISIBLE
                    viewModel.search(etSearchProduct.text.toString())
                } else {
                    Toast.makeText(this, "Debes ingresar más de 3 letras", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        viewModel.products.observe(this, {
            adapter.setData(it)

            if(it.isEmpty()) {
                iEmptyList.visibility = View.VISIBLE
                rvProducts.visibility = View.GONE
            } else {
                iEmptyList.visibility = View.GONE
                rvProducts.visibility = View.VISIBLE
            }

            pbSearching.visibility = View.GONE
        })
    }

    override fun onSearchSuccess() {

    }

    override fun onSearchError(message: String?) {
        Log.d(localClassName, "Error: $message")
        Toast.makeText(
            this,
            "Hubo un error, por favor reintenta nuevamente",
            Toast.LENGTH_SHORT).show()
    }
}