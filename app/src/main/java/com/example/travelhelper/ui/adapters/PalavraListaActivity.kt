package com.example.travelhelper.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhelper.R
import com.example.travelhelper.data.models.Palavra

class PalavraAdapter(private var palavras: List<Palavra>) :
    RecyclerView.Adapter<PalavraAdapter.PalavraViewHolder>() {

    class PalavraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val palavraBase: TextView = itemView.findViewById(R.id.txtPalavraBase)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PalavraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_palavra, parent, false)
        return PalavraViewHolder(view)
    }

    override fun onBindViewHolder(holder: PalavraViewHolder, position: Int) {
        holder.palavraBase.text = palavras[position].palavraBase
    }

    override fun getItemCount(): Int = palavras.size

    fun updateList(novasPalavras: List<Palavra>) {
        palavras = novasPalavras
        notifyDataSetChanged()
    }
}
