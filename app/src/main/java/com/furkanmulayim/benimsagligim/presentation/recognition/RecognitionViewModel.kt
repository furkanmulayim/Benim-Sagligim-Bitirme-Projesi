package com.furkanmulayim.benimsagligim.presentation.recognition

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.SharedPrefs

class RecognitionViewModel(application: Application) : BaseViewModel(application) {

    //shared preferences nesnesi
    private var sp = SharedPrefs(getApplication())

    //gelen urlyi parse etmek
     fun parsUrl(): Uri {
        //Uri türünden görselimi var
        return Uri.parse(sp.getImageUriInShared())
    }

    fun navigate(view: View, pageId: NavDirections) {
        Navigation.findNavController(view).navigate(pageId)
    }

}