package com.maxwell.mercadolibredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxwell.mercadolibredemo.network.models.Product
import com.maxwell.mercadolibredemo.ui.product_details.AttrProdAdapter

class DetailsProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_product)

        val product: Product = intent.getParcelableExtra("product")!!

        val ivProductImage: ImageView = findViewById(R.id.ivProductImage)
        Glide.with(this).load(product.thumbnail).into(ivProductImage)

        val tvProdTitle: TextView = findViewById(R.id.tvProdTitle)
        tvProdTitle.text = product.title

        val ratings = product.seller?.seller_reputation!!.transactions.ratings
        val tvSellerReputationPositive: TextView = findViewById(R.id.tvSellerReputationPositive)
        val tvSellerReputationNeutral: TextView = findViewById(R.id.tvSellerReputationNeutral)
        val tvSellerReputationNegative: TextView = findViewById(R.id.tvSellerReputationNegative)
        tvSellerReputationPositive.text = "Positivo\n${(ratings.positive * 100).toInt()}%"
        tvSellerReputationNeutral.text = "Neutral\n${(ratings.neutral * 100).toInt()}%"
        tvSellerReputationNegative.text = "Negativo\n${(ratings.negative * 100).toInt()}%"

        val rvProdAttr: RecyclerView = findViewById(R.id.rvProdAttr)
        val adapter = AttrProdAdapter(product.attributes!!)
        rvProdAttr.adapter = adapter
        rvProdAttr.layoutManager = LinearLayoutManager(this)
        rvProdAttr.setHasFixedSize(true)
    }
}