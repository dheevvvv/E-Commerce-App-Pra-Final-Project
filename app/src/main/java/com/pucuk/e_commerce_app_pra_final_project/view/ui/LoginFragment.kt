package com.pucuk.e_commerce_app_pra_final_project.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pucuk.e_commerce_app_pra_final_project.R
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoopeeapplication.Network.ApiClient
import com.pucuk.e_commerce_app_pra_final_project.databinding.FragmentLoginBinding
import com.pucuk.e_commerce_app_pra_final_project.model.users_response.DataUsersResponseItem
import com.pucuk.e_commerce_app_pra_final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("ReplaceGetOrSet", "RemoveToStringInStringTemplate", "ReplaceCallWithBinaryOperator")
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userVM: UserViewModel
    lateinit var sharepref: SharedPreferences

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

        sharepref = requireContext().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()
            auth(email, password)
//            if (binding.etEmaillogin.text.toString().isEmpty()){
//                binding.etEmaillogin.setError("Isi Username")
//            }else if(binding.etPasswordlogin.text.toString().isEmpty()){
//                binding.etPasswordlogin.setError("Isi Password")
//            }else{
////                forLogin()
//            }
        }

        binding.tvToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun auth(email: String, password: String) {
        ApiClient.instance.getAllUser()
            .enqueue(object : Callback<List<DataUsersResponseItem>> {
                override fun onResponse(
                    call: Call<List<DataUsersResponseItem>>,
                    response: Response<List<DataUsersResponseItem>>,
                ) {
                    if (response.isSuccessful) {
                        val resBody = response.body()
                        if (resBody != null) {
                            Log.d(tag, "RESPONSE : ${resBody.toString()}")
                            for (i in resBody.indices) {
                                if (resBody[i].email.equals(email) && resBody[i].password.equals(
                                        password
                                    )
                                ) {
                                    val addData = sharepref.edit()
                                    addData.putString("email", resBody[i].email)
                                    addData.putString("username", resBody[i].name)
                                    addData.putString("password", resBody[i].password)
                                    addData.putString("id", resBody[i].idUsers)
                                    addData.apply()

                                    // Clear error text
                                    binding.etEmailLogin.error = null
                                    binding.etPasswordLogin.error = null

                                    Navigation.findNavController(requireView())
                                        .navigate(R.id.action_loginFragment_to_homeFragment)
                                    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    // Set error text
                                    binding.etPasswordLogin.error = "Password Tidak Sesuai"
                                    binding.etEmailLogin.error = "Email Tidak Sesuai"
                                    Toast.makeText(
                                        context,
                                        "Invalid Username or Password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, "Gagal Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<DataUsersResponseItem>>, t: Throwable) {
                    Toast.makeText(context, "Kesalahan", Toast.LENGTH_SHORT).show()
                }

            })
    }

}