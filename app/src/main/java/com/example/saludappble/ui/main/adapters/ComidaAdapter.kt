package com.example.saludappble.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saludappble.databinding.ComidaBinding
import com.example.saludappble.entities.Comida

class ComidaAdapter() : RecyclerView.Adapter<ComidaViewHolder>() {

    private var lista: List<Comida> = listOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ComidaViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val comidaDataBinding = ComidaBinding.inflate(inflater, viewGroup, false)
        return ComidaViewHolder(comidaDataBinding)
    }

    override fun onBindViewHolder(viewHolder: ComidaViewHolder, i: Int) {
        viewHolder.bind(lista[i])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setComidas(comidas: List<Comida>) {
        lista = comidas
        notifyDataSetChanged()
    }
}