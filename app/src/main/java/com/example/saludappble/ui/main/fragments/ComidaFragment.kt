package com.example.saludappble.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.saludappble.databinding.ComidaFragmentBinding
import com.example.saludappble.ui.main.MainViewModel
import kotlinx.android.synthetic.main.comida_fragment.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ComidaFragment(private val tipoComida:String) : Fragment() {
    private lateinit var mView: View
    private lateinit var mBinding: ComidaFragmentBinding

    private val model: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ComidaFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.mainViewModel = model
        binding.lifecycleOwner = viewLifecycleOwner

        this.mBinding = binding
        this.mView = view

        if(tipoComida == "DESAYUNO" || tipoComida == "MERIENDA"){
            mView.viewPostre.visibility = View.GONE
            mView.tvPostre.visibility = View.GONE
            mView.swPostre.visibility = View.GONE
            mView.etPostre.visibility = View.GONE
        }


        return view
    }


}