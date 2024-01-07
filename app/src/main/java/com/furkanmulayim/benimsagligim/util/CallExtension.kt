package com.furkanmulayim.benimsagligim.util

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Activity.startCallWithPermission(phoneNumber: String, requestCode: Int) {
    if (checkCallPermission()) {
        //izin verilmiş ise aramayı başlatma fonksiyonu
        startCall(phoneNumber)
    } else {
        //izin verilmemiş ise izin al
        requestCallPermission(requestCode)
    }
}

private fun Activity.checkCallPermission(): Boolean {
    //İzinleri kontrol et
    val permission = Manifest.permission.CALL_PHONE
    val granted = PackageManager.PERMISSION_GRANTED
    return ContextCompat.checkSelfPermission(this, permission) == granted
}

private fun Activity.requestCallPermission(requestCode: Int) {
    //Kullanıcıdan izin iste
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.CALL_PHONE),
        requestCode
    )
}

private fun Activity.startCall(phoneNumber: String) {
    //aramayı başlat
    val callIntent = Intent(Intent.ACTION_CALL)
    callIntent.data = Uri.parse("tel:$phoneNumber")
    startActivity(callIntent)
}
