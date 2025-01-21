package com.example.travelhelper.services

import android.content.Context
import android.util.Log
import com.example.travelhelper.utils.ApiKeys
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

object MapsService {

    fun initMap(context: Context, mapFragment: SupportMapFragment, latitude: Double, longitude: Double) {
        mapFragment.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {
                val userLocation = LatLng(latitude, longitude)
                googleMap.addMarker(MarkerOptions().position(userLocation).title("Você está aqui"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))

                Log.d("MapsService", "Mapa carregado com sucesso!")
            }
        })
    }
}
