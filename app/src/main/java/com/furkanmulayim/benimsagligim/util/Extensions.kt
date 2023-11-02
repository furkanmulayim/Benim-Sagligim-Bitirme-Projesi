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
fun hastalikRiskOraniHesapla(oran: String):String {
    val orani: Int = oran.toInt()
    var s = ""

    s = if (orani in 1..29){
        "Düşük Derece Risk"
    }
    else if (orani in 30..49){
        "Orta Derece Risk"
    }
    else {
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
    back = if (this == "Düşük Derece Risk"){
        R.drawable.r_3
    }
    else if (this =="Orta Derece Risk"){
        R.drawable.r_2
    }
    else {
        R.drawable.r_1
    }
    return back
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
    val opt = RequestOptions().placeholder(progressDrawable).error(R.color.white)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this)
}

