package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutUsFragment_to_accountFragment)
        }
    }

}