package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentProductBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.ProductAdapter
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productViewModel: ProductViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataCategoryId = arguments?.getInt("categoryId")
        val dataCategoryNama = arguments?.getString("categoryNama")

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.callApiGetAllProductByCategory(dataCategoryId!!)
        productViewModel.product.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                productAdapter = ProductAdapter(it)
                binding.rvProduct.layoutManager = GridLayoutManager(context, 3)
                binding.rvProduct.adapter = productAdapter
            }
        })
    }


}