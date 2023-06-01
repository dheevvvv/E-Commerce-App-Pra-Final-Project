package com.pucuk.e_commerce_app_pra_final_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemHistoryTransactionBinding
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemProductCategoryBinding
import com.pucuk.e_commerce_app_pra_final_project.model.category_product_response.DataCategoryProductResponseItem
import com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response.DataHistoryTransactionResponseItem

class CategoryProductAdapter (private var listCategoryProduct: List<DataCategoryProductResponseItem>)
    : RecyclerView.Adapter<CategoryProductAdapter.ViewHolder>() {

    var onClick: ((DataCategoryProductResponseItem)->Unit)? = null

    class ViewHolder(var binding: ItemProductCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCategoryProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val categoryProductItem = listCategoryProduct[position]
        Glide.with(holder.itemView).load(categoryProductItem.image).into(holder.binding.ivImgProduct)
        holder.binding.tvNamaCategoryProduct.text = categoryProductItem.name
//        holder.binding.tvNamaCategoryProduct.text =
        holder.binding.KlikCategory.setOnClickListener {
            onClick!!.invoke(categoryProductItem)
        }

    }
}