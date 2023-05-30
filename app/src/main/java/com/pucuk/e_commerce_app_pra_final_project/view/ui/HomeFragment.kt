package com.pucuk.e_commerce_app_pra_final_project.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentHomeBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.NewsAdapter
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.HomeViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModelHome: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNews()
    }

    fun getNews(){
        viewModelHome = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModelHome.getListNews()
        viewModelHome.dataNews.observe(viewLifecycleOwner, Observer{
            binding.rvListnewshome.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            if (it!= null) {
                binding.rvListnewshome.adapter = NewsAdapter(it)
            }
        })
    }



}
