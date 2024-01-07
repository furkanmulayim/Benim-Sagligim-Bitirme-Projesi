package com.furkanmulayim.benimsagligim.presentation.pill

import android.app.Application
import android.view.View
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.SharedPrefs

class PillViewModel(application: Application) : BaseViewModel(application) {

    private var sp = SharedPrefs(getApplication())

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }

    fun gorselUriKaydet(imageUri: String) {
        sp.saveImageLocation(imageUri)
    }


}