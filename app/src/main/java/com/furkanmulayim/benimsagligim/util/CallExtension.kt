package com.furkanmulayim.benimsagligim.util

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Activity.startCallWithPermission(phoneNumber: String, requestCode: Int) {
    // Arama yapmadan önce gerekli izinleri kontrol et
    if (checkCallPermission()) {
        startCall(phoneNumber)
    } else {
        requestCallPermission(requestCode)
    }
}

private fun Activity.checkCallPermission(): Boolean {
    val permission = Manifest.permission.CALL_PHONE
    val granted = PackageManager.PERMISSION_GRANTED
    return ContextCompat.checkSelfPermission(this, permission) == granted
}

private fun Activity.requestCallPermission(requestCode: Int) {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.CALL_PHONE),
        requestCode
    )
}

private fun Activity.startCall(phoneNumber: String) {
    val callIntent = Intent(Intent.ACTION_CALL)
    callIntent.data = Uri.parse("tel:$phoneNumber")
    startActivity(callIntent)
}
