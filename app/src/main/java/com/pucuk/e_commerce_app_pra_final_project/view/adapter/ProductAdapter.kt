package com.pucuk.e_commerce_app_pra_final_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemProductBinding
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemProductCategoryBinding
import com.pucuk.e_commerce_app_pra_final_project.model.category_product_response.DataCategoryProductResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.product_response.DataProductsResponseItem

class ProductAdapter (private var listProduct: List<DataProductsResponseItem>)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = listProduct[position]
        Glide.with(holder.itemView).load(productItem.productImage).into(holder.binding.ivImgProduct)
        holder.binding.tvNamaProduct.text = productItem.name
        holder.binding.tvPriceProduct.text = productItem.price

    }
}