package com.example.travelhelper.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelhelper.R
import com.example.travelhelper.data.db.DatabaseHelper
import com.example.travelhelper.data.db.PalavraDao
import com.example.travelhelper.data.repositories.PalavraRepository
import com.example.travelhelper.ui.adapters.PalavraAdapter
import com.example.travelhelper.ui.viewmodels.MainViewModel
import com.example.travelhelper.ui.viewmodels.MainViewModelFactory
import java.util.Locale

class PalavraListaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var palavraAdapter: PalavraAdapter
    private val viewModel: MainViewModel by viewModels {
        val databaseHelper = DatabaseHelper(this)
        val palavraDao = PalavraDao(databaseHelper)
        val palavraRepository = PalavraRepository(palavraDao)
        MainViewModelFactory(palavraRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palavra_lista)

        recyclerView = findViewById(R.id.recyclerView_palavras)
        recyclerView.layoutManager = LinearLayoutManager(this)
        palavraAdapter = PalavraAdapter(emptyList())
        recyclerView.adapter = palavraAdapter

        viewModel.carregarPalavras()

        viewModel.palavras.observe(this) { palavras ->
            palavraAdapter.updateList(palavras)
        }
    }
}