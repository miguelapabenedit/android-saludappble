package com.example.saludappble.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saludappble.databinding.MainFragmentBinding
import com.example.saludappble.ui.main.MainViewModel
import com.example.saludappble.ui.main.adapters.ComidaAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
    private lateinit var mView: View
    private lateinit var mBinding: MainFragmentBinding

    private val model: MainViewModel by sharedViewModel()
    private lateinit var listComidas: RecyclerView
    private var comidaAdapter = ComidaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.mainViewModel = model
        binding.lifecycleOwner = viewLifecycleOwner

        this.mBinding = binding
        this.mView = view

        setRecyclerView()
        setObservers()
        model.getComidas()

        return view
    }

    private fun setRecyclerView() {
        listComidas = mBinding.rvComidas
        setViewRecycler(listComidas)
        listComidas.adapter = comidaAdapter
    }

    private fun setViewRecycler(list: RecyclerView){
        list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        list.setHasFixedSize(true)
    }

    private fun setObservers(){
        model.comidas.observe(viewLifecycleOwner,{
            it?.let {
                comidaAdapter.setComidas(it)
            }
        })
    }
}