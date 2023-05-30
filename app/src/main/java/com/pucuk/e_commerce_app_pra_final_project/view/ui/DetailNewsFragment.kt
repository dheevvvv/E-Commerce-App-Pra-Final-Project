package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentDetailNewsBinding
import com.pucuk.e_commerce_app_pra_final_project.model.news_response.DataNewsResponseItem
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.HomeViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    private lateinit var binding: FragmentDetailNewsBinding
    private lateinit var viewModel: HomeViewModel
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
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val id = arguments?.getInt("ID")
        if (id != null) {
            viewModel.getNewsById(id)
            observeDetailNews()
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

                    binding.txtDescNews.text = """Overview:
                        |
                    """.trimMargin() + it.content.toString()
                }
            }
        }
    }

}
