package com.pucuk.e_commerce_app_pra_final_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemHistoryTransactionBinding
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemKeranjangBinding
import com.pucuk.e_commerce_app_pra_final_project.model.transaction_history_response.DataHistoryTransactionResponseItem
import com.pucuk.e_commerce_app_pra_final_project.view.ui.HistoryTransactionFragment

class HistoryTransactionAdapter(private var listHistoryTransaction: List<DataHistoryTransactionResponseItem>)
    :RecyclerView.Adapter<HistoryTransactionAdapter.ViewHolder>() {

    class ViewHolder(var binding:ItemHistoryTransactionBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHistoryTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listHistoryTransaction.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val historyTransactionItem = listHistoryTransaction[position]
        Glide.with(holder.itemView).load(historyTransactionItem.picture).into(holder.binding.ivImgProduct)
        holder.binding.tvDateTransactionHistory.text = historyTransactionItem.createdAt
        holder.binding.tvNamaProduct.text = historyTransactionItem.name
        holder.binding.tvJumlahProduk.text = historyTransactionItem.amount.toString()
        holder.binding.tvTotalHargaProduk.text = historyTransactionItem.total.toString()
    }
}