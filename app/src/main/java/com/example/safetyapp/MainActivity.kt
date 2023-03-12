package com.example.safetyapp

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val permission = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.READ_CONTACTS
    )
    val permissionCode = 78
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val user = hashMapOf(
            "first_name" to "Lokesh",
            "last_name" to "Kedia",
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("", "Error adding document", e)
            }

        askForPermission()

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_bar)
//        replaceFragment(HomeFragment())
        bottomBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_home -> {
                    inflateFragment(HomeFragment.newInstance())
                }
                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())

                }
                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
                R.id.nav_dashboard -> {
                    inflateFragment(MapsFragment())
                }
            }

            true
        }
        bottomBar.selectedItemId = R.id.nav_home
    }

    private fun askForPermission() {

        ActivityCompat.requestPermissions(this, permission, permissionCode)

    }

//    private fun replaceFragment(homeFragment:Fragment) {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction =  fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.container,homeFragment)
//        fragmentTransaction.commit()
//    }

    private fun inflateFragment(newInstance: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {
            if (allPermissionGranted()) {

            } else {

            }
        }
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permission) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    item
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

}