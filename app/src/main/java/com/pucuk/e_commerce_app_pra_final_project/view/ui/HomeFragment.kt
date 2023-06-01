package com.pucuk.e_commerce_app_pra_final_project.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentHomeBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.NewsAdapter
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.HomeViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.pucuk.e_commerce_app_pra_final_project.R
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment()  {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModelHome: HomeViewModel
    private val imageList = arrayListOf<SlideModel>()
    data class NewsSlideModel(val imageUrl: String, val title: String)
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

    fun getNews() {
        viewModelHome = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModelHome.getListNews()
        viewModelHome.dataNews.observe(viewLifecycleOwner, Observer { newsList ->
//            binding.rvProduct.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            if (newsList != null) {
                val newsSlideList = newsList.map { news ->
                    NewsSlideModel(news.newsImage, news.title)
                }
                for (newsSlide in newsSlideList) {
                    imageList.add(
                        SlideModel(newsSlide.imageUrl, newsSlide.title)
                    )
                }
                binding.imageSlider.setImageList(imageList)

                binding.imageSlider.setItemClickListener(object : ItemClickListener {
                    override fun doubleClick(position: Int) {
                        TODO("Not yet implemented")
                    }

                    override fun onItemSelected(position: Int) {
                        val selectedNews = newsList[position] // Memperoleh item berita terkait dari posisi saat ini
                        val bundle = bundleOf("ID" to selectedNews.idNews) // Mengirimkan ID berita ke DetailNewsFragment
                        findNavController().navigate(R.id.action_homeFragment_to_detailNewsFragment, bundle)
                    }
                })

//                binding.rvProduct.adapter = NewsAdapter(newsList)
            }
        })
    }
}