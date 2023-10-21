package com.furkanmulayim.benimsagligim.presentation.notification_future

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class NotificationViewModel:ViewModel() {

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}