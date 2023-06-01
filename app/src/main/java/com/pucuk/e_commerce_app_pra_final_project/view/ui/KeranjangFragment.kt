package com.pucuk.e_commerce_app_pra_final_project.view.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentKeranjangBinding
import com.pucuk.e_commerce_app_pra_final_project.view.adapter.CartAdapter
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.CartViewModel
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel


class KeranjangFragment : Fragment() {
    private lateinit var binding:FragmentKeranjangBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter
//    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKeranjangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

//        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> {
//                    findNavController().navigate(R.id.action_newsFragment_to_homeFragment)
//                    true
//                }
//                R.id.favorite -> {
//                    findNavController().navigate(R.id.action_newsFragment_to_favoriteFragment)
//                    true
//                }
//                R.id.cart -> {
//                    findNavController().navigate(R.id.action_newsFragment_to_keranjangFragment)
//                    true
//                }
//                R.id.account -> {
//                    findNavController().navigate(R.id.action_newsFragment_to_accountFragment)
//                    true
//                }
//
//                else -> false
//            }
//        }



//        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//        userViewModel.userId.observe(viewLifecycleOwner, Observer {
////            cartViewModel.callApiCart(it)
////            cartViewModel.cart.observe(viewLifecycleOwner, Observer {
////                val cartItems = it
////                cartAdapter = CartAdapter(cartItems, requireContext(), {position, quantity ->
////                    cartAdapter.notifyDataSetChanged()
////                    calculateTotalPrice(quantity)
////                })
////                binding.rvCart.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
////                binding.rvCart.adapter = cartAdapter
////            })
//        })

        cartViewModel.callApiCart(2)
        cartViewModel.cart.observe(viewLifecycleOwner, Observer {
            val cartItems = it
            cartAdapter = CartAdapter(cartItems, requireContext(), {position, quantity ->
                cartAdapter.notifyDataSetChanged()
                calculateTotalPrice(quantity)
            })
            binding.rvCart.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvCart.adapter = cartAdapter
        })
        
    }

    private fun calculateTotalPrice(quantity: Int) {
        var totalPrice = 0
        cartViewModel.cart.observe(viewLifecycleOwner, Observer {
            for (cartItem in it){
                totalPrice += cartItem.price.toInt() * quantity
                totalPrice.toString()
            }
            binding.tvTotalPrice.text = totalPrice.toString()
            binding.tvSubtotal.text = totalPrice.toString()
        })
    }



}