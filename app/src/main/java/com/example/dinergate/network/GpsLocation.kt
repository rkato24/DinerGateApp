package com.example.dinergate.network

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class GpsLocation : AppCompatActivity() {
    private val MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1

    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback : LocationCallback
    private lateinit var _locationRequest: LocationRequest

    public var lat: Double = 0.0
    public var lng: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        _locationRequest = LocationRequest.create()

        checkPermission()

    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            myLocationEnable()
        } else {
            requestLocationPermission()
        }

    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // 許可を求め、拒否されていた場合
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
        } else {
            // まだ許可を求めていない
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION->{
                if (permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 許可された
                    myLocationEnable()
                } else {

                }
            }
        }
    }
    private fun myLocationEnable(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            val locationRequest =  LocationRequest().apply {
                interval = 10000        //最も長い更新時間
                fastestInterval = 5000  //最も短い更新時間
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    //lastLocation = locationResult.lastLocation
                    lat = lastLocation.latitude
                    lng = lastLocation.longitude
                    }
                }
            }
            fusedLocationProviderClient.requestLocationUpdates(_locationRequest,locationCallback,null)
    }

    fun getGpsLocation (): Pair<Double, Double> {
        return Pair(lat, lng)
    }

}
