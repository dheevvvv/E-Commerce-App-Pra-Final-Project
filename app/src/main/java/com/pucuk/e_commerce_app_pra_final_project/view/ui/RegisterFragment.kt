package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoopeeapplication.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userVM: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val username = binding.edtUsernameRegister.text.toString()
        val email = binding.edtEmailRegister.text.toString()
        val password = binding.edtPasswordRegister.text.toString()
        val passwordConfirm = binding.edtUlangiPassword.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            if (password == passwordConfirm) {
                userVM.postUserRegister(username, email, password)
                Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_registerFragment2_to_loginFragment2)
            } else {
                Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}