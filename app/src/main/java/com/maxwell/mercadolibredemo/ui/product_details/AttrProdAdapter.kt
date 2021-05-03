package com.maxwell.mercadolibredemo.ui.product_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.mercadolibredemo.R
import com.maxwell.mercadolibredemo.network.models.Attributes
import com.maxwell.mercadolibredemo.ui.search.SearchVH

class AttrProdVH(v: View) : RecyclerView.ViewHolder(v) {
    val tvName: TextView = v.findViewById(R.id.tvName)
    val tvValue: TextView = v.findViewById(R.id.tvValue)
}

class AttrProdAdapter(private val attrs: List<Attributes>): RecyclerView.Adapter<AttrProdVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttrProdVH {
        return AttrProdVH(LayoutInflater.from(parent.context).inflate(R.layout.item_product_detail_attr, parent, false))
    }

    override fun onBindViewHolder(holder: AttrProdVH, position: Int) {
        val attr = attrs[position]

        holder.tvName.text = attr.name
        holder.tvValue.text = attr.value_name?: ""
    }

    override fun getItemCount(): Int {
        return attrs.size
    }
}