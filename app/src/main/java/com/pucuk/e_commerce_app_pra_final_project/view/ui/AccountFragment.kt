package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentAccountBinding
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

//        userViewModel.callGetApiAllUser()
//        userViewModel.users.observe(viewLifecycleOwner, Observer {
//            it
//        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
                    true
                }
                R.id.news -> {
                    findNavController().navigate(R.id.action_accountFragment_to_newsFragment)
                    true
                }
                R.id.favorite -> {
                    findNavController().navigate(R.id.action_accountFragment_to_favoriteFragment)
                    true
                }
                R.id.cart -> {
                    findNavController().navigate(R.id.action_accountFragment_to_keranjangFragment)
                    true
                }

                else -> false
            }
        }
    }

}