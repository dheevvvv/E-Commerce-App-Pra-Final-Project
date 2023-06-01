package com.pucuk.e_commerce_app_pra_final_project

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private lateinit var  navController: NavController
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navController = findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNavigationView.setupWithNavController(navController)
//
//        bottomNavigationView.setOnNavigationItemSelectedListener {item ->
//            when (item.itemId){
//                R.id.home -> {
//                    navController.navigate(R.id.homeFragment)
//                    true
//                }
//                R.id.cart -> {
//                    navController.navigate(R.id.keranjangFragment)
//                    true
//                }
//                R.id.news -> {
//                    navController.navigate(R.id.newsFragment)
//                    true
//                }
//                R.id.favorite -> {
//                    navController.navigate(R.id.wishlistFragment)
//                    true
//                }
//                else -> false
//            }
//        }
    }
}