package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentHistoryTransactionBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.HistoryTransactionAdapter
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.HistoryTransactionViewModel
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel


class HistoryTransactionFragment : Fragment() {

    private lateinit var binding: FragmentHistoryTransactionBinding
    private lateinit var historyTransactionViewModel: HistoryTransactionViewModel
    private lateinit var historyTransactionAdapter: HistoryTransactionAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyTransactionViewModel = ViewModelProvider(this).get(HistoryTransactionViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getUserId()
        userViewModel.userId.observe(viewLifecycleOwner, Observer {
            historyTransactionViewModel.callApiHistoryTrans(it)
            historyTransactionViewModel.historyTrans.observe(viewLifecycleOwner, Observer {
                if (it!= null){
                    historyTransactionAdapter = HistoryTransactionAdapter(it)
                    binding.rvHistoryTransation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.rvHistoryTransation.adapter = historyTransactionAdapter
                }
            })
        })

    }


}