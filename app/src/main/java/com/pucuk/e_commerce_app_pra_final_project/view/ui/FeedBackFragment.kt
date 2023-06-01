package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentFeedBackBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class FeedBackFragment : Fragment() {

    lateinit var binding: FragmentFeedBackBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_back, container, false)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_feedBackFragment_to_accountFragment)
        }
    }

}