package com.luanasilva.fotosdegatos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.luanasilva.fotosdegatos.databinding.LayoutGatoColunasBinding
import com.squareup.picasso.Picasso

class GatoAdapter(): Adapter<GatoAdapter.GatoViewHolder>() {

    private var listaImagensGatos = emptyList<String>()

    fun adicionarLista(lista: List<String>) {
        listaImagensGatos = lista
    }

    inner class GatoViewHolder(val binding: LayoutGatoColunasBinding)
        : ViewHolder(binding.root){

            fun bind(url : String) {

                Picasso.get()
                    .load(url)
                    .into(binding.imageGatoView)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : GatoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = LayoutGatoColunasBinding.inflate(
            layoutInflater,parent,false
        )

        return GatoViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listaImagensGatos.size
    }

    override fun onBindViewHolder(holder: GatoViewHolder, position: Int) {
        val url = listaImagensGatos[position]

        holder.bind(url)
    }

}