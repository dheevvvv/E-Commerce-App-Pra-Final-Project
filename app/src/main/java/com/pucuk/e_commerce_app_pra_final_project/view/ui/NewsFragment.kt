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
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentNewsBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.MainNewsAdapter
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    lateinit var viewModelHome: HomeViewModel
    lateinit var userViewModel: UserViewModel

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

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUserId()
        userViewModel.userId.observe(viewLifecycleOwner, Observer {
            val userId = it
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_newsFragment_to_homeFragment)
                    true
                }
                R.id.favorite -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_newsFragment_to_favoriteFragment)
                        } else{
                            findNavController().navigate(R.id.action_newsFragment_to_loginFragment)
                        }
                    })
                    true
                }
                R.id.cart -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_newsFragment_to_keranjangFragment)
                        } else{
                            findNavController().navigate(R.id.action_newsFragment_to_loginFragment)
                        }
                    })
                    true
                }
                R.id.account -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_newsFragment_to_accountFragment)
                        } else{
                            findNavController().navigate(R.id.action_newsFragment_to_loginFragment)
                        }
                    })
                    true
                }

                else -> false
            }
        }

        getNews()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun getNews() {
        viewModelHome = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModelHome.getListNews()
        viewModelHome.dataNews.observe(viewLifecycleOwner, Observer { newsList ->
            if (newsList != null) {
                binding.rvMainNews.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = MainNewsAdapter(newsList)
                    isNestedScrollingEnabled = false // Disable nested scrolling if using NestedScrollView
                }
            }
        })
    }

}
