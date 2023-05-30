package com.pucuk.e_commerce_app_pra_final_project.view.adapter

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.databinding.ItemKeranjangBinding
import com.pucuk.e_commerce_app_pra_final_project.model.cart_response.DataCartResponseItem

class CartAdapter(private val listCart:List<DataCartResponseItem>,
                  private val context:Context,
                  private val onQuantityChanged: (position:Int, quantity:Int)->Unit,)
    :RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemKeranjangBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemKeranjangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val cartItem = listCart[position]
        Glide.with(holder.itemView).load(cartItem.productImage).into(holder.binding.ivImgProduct)
        holder.binding.tvNamaProduct.text = cartItem.name
        holder.binding.tvHargaCartproduk.text = cartItem.price

        // Set up spinner for quantity
        val quantities = (1..10).toList()
        val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, quantities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.binding.spJumlahProduk.adapter = adapter

// Set selected quantity from cart item
        val selectedQuantityIndex = 1
        holder.binding.spJumlahProduk.setSelection(selectedQuantityIndex)

// Handle quantity changes
        holder.binding.spJumlahProduk.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val newQuantity = quantities[pos]
                onQuantityChanged.invoke(position, newQuantity)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

    }
}