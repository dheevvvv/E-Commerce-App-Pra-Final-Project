package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentAccountBinding
import com.pucuk.e_commerce_app_pra_final_project.datastore_prefs.UserManager
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userManager: UserManager

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
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userManager = UserManager.getInstance(requireContext())
        userViewModel.getUserId()
        userViewModel.userId.observe(viewLifecycleOwner, Observer {
            userViewModel.getDetailUsers(it)
            userViewModel.detailUsers.observe(viewLifecycleOwner, Observer { dataUser ->
                binding.tvUsername.text = dataUser.name
                Glide.with(this).load(dataUser.image)
                    .circleCrop()
                    .into(binding.ivProfileImage)
            })
        })

//        userViewModel.callGetApiAllUser()
//        userViewModel.users.observe(viewLifecycleOwner, Observer {
//
//        })

        binding.apply {
            btnAboutUs.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_aboutUsFragment)
            }
            btnGiveFeedback.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_feedBackFragment)
            }
            btnLogout.setOnClickListener {
                GlobalScope.async {
                    userManager.clearData()
                }
                findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
                Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show()
            }
        }

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
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_accountFragment_to_favoriteFragment)
                        } else{
                            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
                        }
                    })
                    true
                }
                R.id.cart -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_accountFragment_to_keranjangFragment)
                        } else{
                            findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
                        }
                    })
                    true
                }

                else -> false
            }
        }
    }

}