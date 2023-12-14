package com.furkanmulayim.benimsagligim.presentation.detection_future

import android.app.Application
import androidx.fragment.app.Fragment
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel

class DetectionViewModel(application: Application) : BaseViewModel(application) {

    fun getList(char: Char, rf: Fragment): List<String> {
        var list = listOf<String>()
        when (char) {
            'a' -> {
                list = stringToList((rf.getString(R.string.genelsemp)))
            }

            'b' -> {
                list = stringToList((rf.getString(R.string.digersemp)))
            }

            'c' -> {
                list = stringToList((rf.getString(R.string.solsemp)))
            }

            'd' -> {
                list = stringToList((rf.getString(R.string.norsemp)))
            }

            'e' -> {
                list = stringToList((rf.getString(R.string.bilsemp)))
            }

            'f' -> {
                list = stringToList((rf.getString(R.string.kassemp)))
            }

            'g' -> {
                list = stringToList((rf.getString(R.string.bossemp)))
            }

            'h' -> {
                list = stringToList((rf.getString(R.string.gensemp)))
            }
        }
        return list
    }

    fun stringToList(name: String): List<String> {
        // semptomları virgülle ayırarak listeye atıyoruuz
        return name.split(",")
    }


}