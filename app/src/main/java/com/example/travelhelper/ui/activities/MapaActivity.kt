package com.example.travelhelper.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelhelper.R
import com.example.travelhelper.services.MapsService
import com.example.travelhelper.utils.LocationUtils
import com.google.android.gms.maps.SupportMapFragment

class MapaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment

        LocationUtils.getUserLocation(
            context = this,
            onSuccess = { location ->
                if (location != null) {
                    MapsService.initMap(this, mapFragment, location.latitude, location.longitude)
                }
            },
            onFailure = { exception ->
                exception.printStackTrace()
            }
        )
    }
}
