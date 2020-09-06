package com.cep.cep_podcast.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MarshMallowPermissionUtils(private val activity: Activity) {

    companion object{
        const val EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1
    }

    fun checkPermissionForWriteExternalStorage(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForReadExternalStorage(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }



    fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.CAMERA
            )
        ) {
            Toast.makeText(activity, "Permission denied", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE
            )
        }
    }
}