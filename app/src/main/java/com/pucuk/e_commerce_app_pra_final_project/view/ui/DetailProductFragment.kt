package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentDetailProductBinding
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.ProductViewModel

class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding
    private lateinit var productViewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataIdCategory = arguments?.getInt("idCategory")
        val dataIdProduct = arguments?.getInt("idProduct")
        val datanNamaCategory = arguments?.getString("namaCategory")

        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        productViewModel.getDetailProduct(dataIdCategory!!, dataIdProduct!!)
        productViewModel.detailProduct.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.productImage)
                .circleCrop()
                .into(binding.ivPhoto)
            binding.tvName.text = it.name
            binding.tvDescription.text = it.description
            binding.tvHarga.text = it.price
            binding.tvCategoryName.text = datanNamaCategory.toString()
        })


    }




}