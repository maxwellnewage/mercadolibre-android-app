package com.maxwell.mercadolibredemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.mercadolibredemo.ui.SearchAdapter
import com.maxwell.mercadolibredemo.ui.SearchViewModel

class MainActivity : AppCompatActivity(), SearchViewModel.OnSearchResponse {
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = SearchViewModel(this)

        val iEmptyList: View = findViewById(R.id.iEmptyList)
        val rvProducts: RecyclerView = findViewById(R.id.rvProducts)
        val adapter = SearchAdapter(this, emptyList())
        rvProducts.adapter = adapter
        rvProducts.layoutManager = LinearLayoutManager(this)

        viewModel.products.observe(this, {
            adapter.setData(it)

            if(it.isEmpty()) {
                iEmptyList.visibility = View.VISIBLE
                rvProducts.visibility = View.GONE
            } else {
                iEmptyList.visibility = View.GONE
                rvProducts.visibility = View.VISIBLE
            }
        })

        viewModel.search("android")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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