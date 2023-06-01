package com.pucuk.e_commerce_app_pra_final_project.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.HomeViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentNewsBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.MainNewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    lateinit var viewModelHome: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNews()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun getNews(){
        viewModelHome = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModelHome.getListNews()
        viewModelHome.dataNews.observe(viewLifecycleOwner, Observer{
            binding.rvMainNews.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            if (it!= null) {
                binding.rvMainNews.adapter = MainNewsAdapter(it)
            }
        })
    }
}
