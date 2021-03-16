package com.maxwell.mercadolibredemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.maxwell.mercadolibredemo.network.MeLiBuilder
import com.maxwell.mercadolibredemo.network.search.SearchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), SearchViewModel.OnSearchResponse {
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = SearchViewModel(this)

        val btTest: Button = findViewById(R.id.btTest)
        btTest.setOnClickListener { viewModel.search("android") }

        viewModel.products.observe(this, {
            if(it.isNotEmpty()) {
                Log.d(localClassName, "First Product: ${it[0]}")
            }
        })
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