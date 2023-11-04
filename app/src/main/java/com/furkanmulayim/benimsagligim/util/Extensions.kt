package com.furkanmulayim.benimsagligim.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.furkanmulayim.benimsagligim.R

/**
//Görseli indirerek imageview'de gösterme fonksiyonu
fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
val opt = RequestOptions().placeholder(progressDrawable).error(R.color.yellow_100)
Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this)
}
 */

//Klavye Kapatmak için
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//verilen yüzdelik değere göre risk hesaplama
fun hastalikRiskOraniHesapla(oran: String): String {
    val orani: Int = oran.toInt()
    var s = ""

    s = if (orani in 1..10) {
        "Düşük Derece Risk"
    } else if (orani in 11..40) {
        "Orta Derece Risk"
    } else {
        "Yüksek Derece Risk"
    }
    return s
}

//Etiketlerin ilk 3 tanesini göstermek istiyoruz
fun String.hastagsCutTheSmall(): String {
    var s = this.split(',').map { it.trim() }
    s = (s.take(3))
    return s.joinToString(", ")
}

//yüzdelik değere göre background ayarlamak için
fun String.toDrawableResource(): Int {
    var back = 0
    back = if (this == "Düşük Derece Risk") {
        R.drawable.r_3
    } else if (this == "Orta Derece Risk") {
        R.drawable.r_2
    } else {
        R.drawable.r_1
    }
    return back
}

fun String.categoryListeSiraBul(): Array<Int> {
    val a = Array(2) { 0 }

    if (this == "Bulaşıcı Hastalıklar") {
        a[0] = 0
        a[1] = 20
    }
    else if (this == "Nörolojik Hastalıklar") {
        a[0] = 20
        a[1] = 35
    }
    else if (this == "Solunum Sistemi Hastalıkları") {
        a[0] = 35
        a[1] = 47
    }
    else if (this == "Ruhsal Hastalıklar") {
        a[0] = 47
        a[1] = 56
    }
    else if (this == "Üreme Sistemi Hastalıkları") {
        a[0] = 56
        a[1] = 66
    }else if (this == "Boşaltım Sistemi Hastalıkları") {
        a[0] = 56
        a[1] = 66
    }else if (this == "Sindirim Sistemi Hastalıkları") {
        a[0] = 56
        a[1] = 66
    }else if (this == "Kas-İskelet Sistemi Hastalıkları") {
        a[0] = 56
        a[1] = 66
    }

    return a
}

//Görseller Yüklenirken kullanıcıya bildiriyoruz
fun ProgressBarr(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}


//Görselleri indiriyoruz
fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
    val opt = RequestOptions().placeholder(progressDrawable)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this)
}

