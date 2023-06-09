package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentDetailNewsBinding
import com.pucuk.e_commerce_app_pra_final_project.model.news_response.DataNewsResponseItem
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.HomeViewModel
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    private lateinit var binding: FragmentDetailNewsBinding
    private lateinit var viewModel: HomeViewModel
    lateinit var userViewModel: UserViewModel
    private lateinit var selectedNews: DataNewsResponseItem
    private var isFavorite by Delegates.notNull<Boolean>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
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
                    findNavController().navigate(R.id.action_detailNewsFragment_to_homeFragment)
                    true
                }
                R.id.favorite -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_detailNewsFragment_to_favoriteFragment)
                        } else{
                            findNavController().navigate(R.id.action_detailNewsFragment_to_loginFragment)
                        }
                    })
                    true
                }
                R.id.news -> {
                    findNavController().navigate(R.id.action_detailNewsFragment_to_newsFragment)
                    true
                }
                R.id.cart -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_detailNewsFragment_to_keranjangFragment)
                        } else{
                            findNavController().navigate(R.id.action_detailNewsFragment_to_loginFragment)
                        }
                    })
                    true
                }
                R.id.account -> {
                    userViewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                        if (it){
                            findNavController().navigate(R.id.action_detailNewsFragment_to_accountFragment)
                        } else{
                            findNavController().navigate(R.id.action_detailNewsFragment_to_loginFragment)
                        }
                    })
                    true
                }

                else -> false
            }
        }

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val id = arguments?.getInt("ID")
        if (id != null) {
            viewModel.getNewsById(id)
            observeDetailNews()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeDetailNews() {
        viewModel.detailNews.observe(viewLifecycleOwner) {
            binding.apply {
                if (it != null) {
                    binding.txtTitleNews.text = it.title.toString()
                    binding.txtReleaseNews.text = "Release: " + it.createdAt.toString()
                    Glide.with(requireContext())
                        .load("${it.newsImage}")
                        .into(binding.imgNews)

                    binding.txtDescNews.text = """Description:
                        |
                    """.trimMargin() + it.content.toString()
                }
            }
        }
    }

}
