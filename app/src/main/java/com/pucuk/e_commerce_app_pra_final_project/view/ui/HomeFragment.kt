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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.pucuk.e_commerce_app_pra_final_project.R
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModelHome: HomeViewModel
    private val imageList = arrayListOf<SlideModel>()
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

        imageList.add(SlideModel("https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg"))
        imageList.add(SlideModel("https://www.seiu1000.org/sites/main/files/main-images/camera_lense_0.jpeg"))

        val sliderLayout = binding.imageSlider
        sliderLayout.setImageList(imageList)

        binding.tvViewAllNews.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
        }

        getNews()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
                    true
                }
                R.id.favorite -> {
                    findNavController().navigate(R.id.action_homeFragment_to_wishlistFragment)
                    true
                }
                R.id.cart -> {
                    findNavController().navigate(R.id.action_homeFragment_to_keranjangFragment)
                    true
                }
                R.id.account -> {
                    findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
                    true
                }
                else -> false
            }
        }
    }

    fun getNews(){
        viewModelHome = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModelHome.getListNews()
        viewModelHome.dataNews.observe(viewLifecycleOwner, Observer{
            binding.rvNews.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            if (it!= null) {
                binding.rvNews.adapter = NewsAdapter(it)
            }
        })
    }
}
