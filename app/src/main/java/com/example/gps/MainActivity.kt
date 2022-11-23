@file:Suppress("DEPRECATION")

package com.example.gps


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.*
import com.google.android.gms.location.*





class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    private lateinit var zdjecie: ImageView
    private lateinit var butt: Button
    private lateinit var Tv1: TextView
    private lateinit var Switch: SwitchCompat

    private lateinit var Tv3: TextView
    private lateinit var Tv5: TextView
    private lateinit var Tv7: TextView
    private lateinit var Tv9: TextView
    private lateinit var Tv2: TextView
    private lateinit var Tv4: TextView
    private lateinit var Tv6: TextView
    private lateinit var Tv8: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zdjecie = findViewById(R.id.zdjecie)
        butt = findViewById(R.id.butt)
        Tv1 = findViewById(R.id.Tv1)
        Switch = findViewById(R.id.Switch)

        Tv3 = findViewById(R.id.Tv3)
        Tv5 = findViewById(R.id.Tv5)
        Tv7 = findViewById(R.id.Tv7)
        Tv9 = findViewById(R.id.Tv9)

        Tv2 = findViewById(R.id.Tv2)
        Tv4 = findViewById(R.id.Tv4)
        Tv6 = findViewById(R.id.Tv6)
        Tv8 = findViewById(R.id.Tv8)

        butt.isEnabled = false
        Tv1.isEnabled = false
        Switch.isEnabled = false


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        }
        else
            butt.isEnabled = true

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 101)
        } else {
            Tv1.isEnabled = true
            Switch.isEnabled = true
        }

        butt.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 102)
        }

        Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                getLastLocation()
                Tv2.visibility = View.VISIBLE
                Tv3.visibility = View.VISIBLE
                Tv4.visibility = View.VISIBLE
                Tv5.visibility = View.VISIBLE
                Tv6.visibility = View.VISIBLE
                Tv7.visibility = View.VISIBLE
                Tv8.visibility = View.VISIBLE
                Tv9.visibility = View.VISIBLE

            } else {
                Tv2.visibility = View.INVISIBLE
                Tv3.visibility = View.INVISIBLE
                Tv4.visibility = View.INVISIBLE
                Tv5.visibility = View.INVISIBLE
                Tv6.visibility = View.INVISIBLE
                Tv7.visibility = View.INVISIBLE
                Tv8.visibility = View.INVISIBLE
                Tv9.visibility = View.INVISIBLE
            }
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }


}

