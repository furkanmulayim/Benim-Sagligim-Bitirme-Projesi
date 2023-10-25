package com.furkanmulayim.benimsagligim.presentation.search_future

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class ScanSearchViewModel: ViewModel() {

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}