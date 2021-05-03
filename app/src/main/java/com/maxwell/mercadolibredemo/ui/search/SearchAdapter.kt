package com.maxwell.mercadolibredemo.ui.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxwell.mercadolibredemo.DetailsProductActivity
import com.maxwell.mercadolibredemo.R
import com.maxwell.mercadolibredemo.commons.Constants
import com.maxwell.mercadolibredemo.network.models.Product

class SearchVH(v: View): RecyclerView.ViewHolder(v) {
    val ivProdPreview: ImageView = v.findViewById(R.id.ivProdPreview)
    val tvProdTitle: TextView = v.findViewById(R.id.tvProdTitle)
    val tvSellerName: TextView = v.findViewById(R.id.tvSellerName)
    // Reputation
    val tvSellerReputationPositive: TextView = v.findViewById(R.id.tvSellerReputationPositive)
    val tvSellerReputationNeutral: TextView = v.findViewById(R.id.tvSellerReputationNeutral)
    val tvSellerReputationNegative: TextView = v.findViewById(R.id.tvSellerReputationNegative)
}

class SearchAdapter(private val context: Context, private var products: List<Product>): RecyclerView.Adapter<SearchVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        return SearchVH(LayoutInflater.from(parent.context).inflate(R.layout.item_product_search, parent, false))
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        val product = products[position]

        Glide.with(context).load(product.thumbnail).into(holder.ivProdPreview)

        holder.tvProdTitle.text = product.title

        // sometimes the API not bring a nickname for the seller
        if(product.seller?.eshop != null) {
            holder.tvSellerName.text = product.seller.eshop.nick_name
        }

        val ratings = product.seller?.seller_reputation!!.transactions.ratings
        // multiplied by 100 and converted to integer for obtain a percentage
        holder.tvSellerReputationPositive.text = "Positivo: ${(ratings.positive * 100).toInt()}%"
        holder.tvSellerReputationNeutral.text = "Neutral: ${(ratings.neutral * 100).toInt()}%"
        holder.tvSellerReputationNegative.text = "Negativo: ${(ratings.negative * 100).toInt()}%"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsProductActivity::class.java)
            intent.putExtra(Constants.EXTRA_PRODUCT, product)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}