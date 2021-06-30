package com.example.saludappble.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.saludappble.databinding.LoginFragmentBinding
import com.example.saludappble.ui.login.LoginViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment: Fragment() {

    private lateinit var mView: View
    private lateinit var mBinding: LoginFragmentBinding

    private val model: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.loginViewModel = model
        binding.lifecycleOwner = viewLifecycleOwner

        this.mBinding = binding
        this.mView = view

        return view
    }
}