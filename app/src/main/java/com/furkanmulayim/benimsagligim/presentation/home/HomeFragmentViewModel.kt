package com.furkanmulayim.benimsagligim.presentation.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class HomeFragmentViewModel : ViewModel() {

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}