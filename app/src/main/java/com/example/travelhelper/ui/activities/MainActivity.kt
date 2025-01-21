package com.example.travelhelper.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.travelhelper.R
import com.example.travelhelper.utils.LocationUtils

class MainActivity : AppCompatActivity() {

    private lateinit var txtUsername: TextView
    private lateinit var btnLogout: Button
    private lateinit var btnListaPalavras: Button
    private lateinit var btnMapa: Button
    private lateinit var btnClima: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkLocationPermission()
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
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            Toast.makeText(this, "Permissão de localização já concedida", Toast.LENGTH_SHORT).show()
            startLocationUpdates()
        }
    }
    private fun startLocationUpdates() {
        LocationUtils.getUserLocation(
            context = this,
            onSuccess = { location ->
                if (location != null) {
                    Toast.makeText(this, "Localização: ${location.latitude}, ${location.longitude}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Localização não encontrada", Toast.LENGTH_SHORT).show()
                }
            },
            onFailure = { exception ->
                Toast.makeText(this, "Erro ao obter localização: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }

}
