package com.pucuk.e_commerce_app_pra_final_project.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.DetailProductViewModel

class DetailProductFragment : Fragment() {

    companion object {
        fun newInstance() = DetailProductFragment()
    }

    private lateinit var viewModel: DetailProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailProductViewModel::class.java)
        // TODO: Use the ViewModel
    }

}