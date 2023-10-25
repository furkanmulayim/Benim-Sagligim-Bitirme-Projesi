package com.furkanmulayim.benimsagligim.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast

/**
 //Görseli indirerek imageview'de gösterme fonksiyonu
fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
    val opt = RequestOptions().placeholder(progressDrawable).error(R.color.yellow_100)
    Glide.with(context).setDefaultRequestOptions(opt).load(url).into(this)
}
*/

//Klavye Kapatma İşlevi
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
