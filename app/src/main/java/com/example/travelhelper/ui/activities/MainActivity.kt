package com.example.travelhelper.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelhelper.R

class MainActivity : AppCompatActivity() {

    private lateinit var txtUsername: TextView
    private lateinit var btnLogout: Button
    private lateinit var btnListaPalavras: Button
    private lateinit var btnMapa: Button
    private lateinit var btnClima: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsername = findViewById(R.id.txtUsername)
        btnLogout = findViewById(R.id.btnLogout)
        btnListaPalavras = findViewById(R.id.btnListaPalavras)
        btnMapa = findViewById(R.id.btnMapa)
        btnClima = findViewById(R.id.btnClima)

        // Obtendo nome do usuário da Intent
        val username = intent.getStringExtra("username")
        txtUsername.text = username

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logout realizado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnListaPalavras.setOnClickListener {
            startActivity(Intent(this, PalavraListaActivity::class.java))
        }

        btnMapa.setOnClickListener {
            startActivity(Intent(this, MapaActivity::class.java))
        }

        btnClima.setOnClickListener {
            startActivity(Intent(this, ClimaActivity::class.java))
        }
    }
}
