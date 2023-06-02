package com.pucuk.e_commerce_app_pra_final_project.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.FavoriteViewModel
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentFavoriteBinding
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel

class FavoriteFragment : Fragment() {


    lateinit var binding: FragmentFavoriteBinding
    lateinit var userViewModel: UserViewModel
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        // TODO: Use the ViewModel

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUserId()
        userViewModel.userId.observe(viewLifecycleOwner, Observer {
            val userId = it
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
                    true
                }
                R.id.news -> {
                    findNavController().navigate(R.id.action_favoriteFragment_to_newsFragment)
                    true
                }
                R.id.cart -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_favoriteFragment_to_keranjangFragment)
                        } else{
                            findNavController().navigate(R.id.action_favoriteFragment_to_loginFragment)
                        }
                    })
                    true
                }
                R.id.account -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_favoriteFragment_to_accountFragment)
                        } else{
                            findNavController().navigate(R.id.action_favoriteFragment_to_loginFragment)
                        }
                    })
                    true
                }

                else -> false
            }
        }
    }



}