package com.example.saludappble.ui.main.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.saludappble.databinding.ComidaBinding
import com.example.saludappble.entities.Comida

class ComidaViewHolder(private val binding: ComidaBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(comida: Comida){
        setComida(comida)
    }

    private fun setComida(comida: Comida){
        binding.tvCardTipoComida.text = comida.tipoComida

        binding.tvCardComidaPrincipal.text = "Principal: ${comida.comidaPrincipal}"
        binding.tvCardComidaSecundaria.text = "Secundaria: ${comida.comidaSecundaria}"
        binding.tvCardBebida.text = "Bebida: ${comida.bebida}"

        if(comida.comidaPostre.isEmpty()){
            binding.tvCardPostre.visibility = View.GONE
        }else{
            binding.tvCardPostre.text = "Postre: ${comida.comidaPostre}"
        }
        if(comida.comidaTentacion.isEmpty()){
            binding.tvCardTentacion.visibility = View.GONE
        }else{
            binding.tvCardTentacion.text = "Se tento con: ${comida.comidaTentacion}"
        }

        binding.tvCardHambre.text = if(comida.hambre){"Se quedo con hambre"}else{"No se quedo con hambre"}
    }
}