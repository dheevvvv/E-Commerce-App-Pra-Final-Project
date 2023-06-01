package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pucuk.e_commerce_app_pra_final_project.R
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentLoginBinding
import com.pucuk.e_commerce_app_pra_final_project.datastore_prefs.UserManager
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var userVM: UserViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        userManager = UserManager.getInstance(requireContext())
        binding.btnLogin.setOnClickListener {
            login()
        }


    }

    private fun login() {
        val email = binding.etEmailLogin.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            // Observasi LiveData dataLoginUser
            userVM.dataLoginUser.observe(viewLifecycleOwner, Observer { dataLoginUser ->
                if (dataLoginUser != null) {
                    // Respons berhasil
                    val userId = dataLoginUser.idUsers.toInt()
                    GlobalScope.async {
                        userManager.saveData(userId, true)
                    }
                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // Respons gagal atau null
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else {
            userVM.loginUser(email, password)
        }
    }
}
