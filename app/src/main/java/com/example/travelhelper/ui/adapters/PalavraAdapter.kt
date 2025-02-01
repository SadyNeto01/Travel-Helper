package com.example.travelhelper.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhelper.R
import com.example.travelhelper.data.models.PalavrasEIdiomas

class PalavraAdapter(
    private var palavras: List<PalavrasEIdiomas>
) : RecyclerView.Adapter<PalavraAdapter.PalavraViewHolder>() {

    class PalavraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val palavraBase: TextView = itemView.findViewById(R.id.tv_palavra_base)
        val traducao: TextView = itemView.findViewById(R.id.tv_traducao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PalavraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_palavra_card, parent, false)
        return PalavraViewHolder(view)
    }

    override fun onBindViewHolder(holder: PalavraViewHolder, position: Int) {
        val palavra = palavras[position]
        holder.palavraBase.text = palavra.pt
        holder.traducao.text = palavra.en // Pode ser ajustado dinamicamente caso precise de outro idioma
    }

    override fun getItemCount(): Int = palavras.size

    fun updateList(novasPalavras: List<PalavrasEIdiomas>) {
        palavras = novasPalavras
        notifyDataSetChanged()
    }
}

