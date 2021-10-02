package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.LoginViewModel

/** Facilitates logging into or creating a new account */
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Inflate the layout for this fragment using data binding and connect the view model
        // that handles the logic with the UI
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        // When the user has created a new account navigate to the welcome destination
        viewModel.eventCreatedAccount.observe(viewLifecycleOwner, { accountCreated ->
            if (accountCreated) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                viewModel.onAccountCreatedComplete()
            }
        }
        )

        // When the user logs in with an existing account navigate to the welcome destination
        viewModel.eventLoginSelected.observe(viewLifecycleOwner, { loginSelected ->
            if (loginSelected) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                viewModel.onLoginSelectedComplete()
            }
        }
        )

        return binding.root
    }

}