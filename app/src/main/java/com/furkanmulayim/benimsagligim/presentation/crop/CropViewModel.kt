package com.furkanmulayim.benimsagligim.presentation.crop

import android.app.Application
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.SharedPrefs

class CropViewModel(application: Application) : BaseViewModel(application) {

    //shared preferences nesnesi
    private var sp = SharedPrefs(getApplication())

    //gelen urlyi parse etmek
    fun getUrl(): String? {
        //Uri türünden görselimi var
        return sp.getImageUriInShared()
    }

    fun navigateWithDirections(view: View, pageId: NavDirections) {
        Navigation.findNavController(view).navigate(pageId)
    }

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }

    fun setImageUri(toString: String) {
        sp.saveImageLocation(toString)
    }

}