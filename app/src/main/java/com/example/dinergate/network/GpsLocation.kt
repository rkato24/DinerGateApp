package com.example.dinergate.network

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/*

GpsLocation : 位置情報の権限のリクエスト、緯度経度の取得を行う。
参考：
https://akira-watson.com/android/kotlin/gps-simple.html
https://developer.android.com/training/permissions/requesting?hl=ja
https://developer.android.com/training/location/permissions?hl=ja
http://www.dicre.com/android/location.html

 */


class GpsLocation : AppCompatActivity(), LocationListener {

    // lateinit: late initialize to avoid checking null
    private lateinit var locationManager: LocationManager
    private var returnLocation: Location? = null

    private val requestPermissionLauncher = registerForActivityResult(
        RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // 使用が許可された
            returnLocation = locationStart()

        } else {
            // それでも拒否された時の対応
            val toast = Toast.makeText(this,
                "これ以上なにもできません", Toast.LENGTH_SHORT)
            toast.show()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            returnLocation = locationStart()
        }
    }

    private fun locationStart() :Location? {
        Log.d("debug", "locationStart()")

        // Instances of LocationManager class must be obtained using Context.getSystemService(Class)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("debug", "location manager Enabled")
        } else {
            // to prompt setting up GPS
            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(settingsIntent)
            Log.d("debug", "not gpsEnable, startActivity")
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1000)

            Log.d("debug", "checkSelfPermission false")
            return null
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            50f,
            this)

        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

    }

    fun getCurrentLocation(): Location? {
        return returnLocation
    }



    override fun onLocationChanged(location: Location) {

    }


    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

}