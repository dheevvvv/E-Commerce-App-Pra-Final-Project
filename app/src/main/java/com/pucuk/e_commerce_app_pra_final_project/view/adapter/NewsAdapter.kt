package com.pucuk.e_commerce_app_pra_final_project.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.model.DataNewsResponseItem
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemNewsBinding
import com.pucuk.e_commerce_app_pra_final_project.model.DataDetailNewsItem

class NewsAdapter(private val listNews: List<DataNewsResponseItem>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: DataDetailNewsItem){
            binding.tvNews.text = newsItem.title
            binding.cardView.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("ID", newsItem.idNews.toString().toInt())
                }
                it.findNavController().navigate(R.id.action_newsFragment_to_detailNewsFragment, bundle)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(listNews[position])
//        Glide.with(holder.itemView).load(listNews[position].newsImage)
    }
}