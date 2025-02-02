package com.example.travelhelper.services

import android.content.Context
import android.location.Location
import android.util.Log
import com.example.travelhelper.utils.LocationUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

object MapsService {

    private var userMarker: Marker? = null

    fun initMap(context: Context, mapFragment: SupportMapFragment) {
        mapFragment.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {
                googleMap.uiSettings.isZoomControlsEnabled = true

                LocationUtils.startLocationUpdates(context) { location ->
                    updateUserLocation(googleMap, location)
                }

                Log.d("MapsService", "Mapa carregado com sucesso!")
            }
        })
    }

    private fun updateUserLocation(googleMap: GoogleMap, location: Location) {
        val userLatLng = LatLng(location.latitude, location.longitude)

        if (userMarker == null) {
            userMarker = googleMap.addMarker(MarkerOptions().position(userLatLng).title("Você está aqui"))
        } else {
            userMarker?.position = userLatLng
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15f))
    }

    fun stopLocationUpdates() {
        LocationUtils.stopLocationUpdates()
    }
}
