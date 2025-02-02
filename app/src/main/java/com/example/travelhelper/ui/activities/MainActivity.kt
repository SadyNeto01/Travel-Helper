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
import com.example.travelhelper.utils.ApiKeys
import com.example.travelhelper.utils.LocationUtils

class MainActivity : AppCompatActivity() {

    private lateinit var txtUsername: TextView
    private lateinit var btnLogout: Button
    private lateinit var btnListaPalavras: Button
    private lateinit var btnMapa: Button
    private lateinit var btnClima: Button
    private lateinit var btnSobre: Button

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsername = findViewById(R.id.txtUsername)
        btnLogout = findViewById(R.id.btnLogout)
        btnListaPalavras = findViewById(R.id.btnListaPalavras)
        btnMapa = findViewById(R.id.btnMapa)
        btnClima = findViewById(R.id.btnClima)
        btnSobre = findViewById(R.id.btnSobre)

        val username = intent.getStringExtra("username")
        txtUsername.text = username ?: "Usuário"

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logout realizado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

        btnSobre.setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }

        setApiKeyMetaData()
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        LocationUtils.startLocationUpdates(this) { location ->
            Toast.makeText(
                this, "Localização atual: ${location.latitude}, ${location.longitude}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "Permissão de localização negada!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationUtils.stopLocationUpdates()
    }

    private fun setApiKeyMetaData() {
        val packageManager = applicationContext.packageManager
        val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        appInfo.metaData.putString("com.google.android.geo.API_KEY", ApiKeys.GOOGLE_MAPS_API_KEY)
    }
}
