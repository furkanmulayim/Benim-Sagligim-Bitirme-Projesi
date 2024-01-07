package com.furkanmulayim.benimsagligim.presentation.notification

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class NotificationViewModel : ViewModel() {
    fun control(
        textTitle: String,
        textDescr: String,
        textTime: String
    ): Boolean {
        var bool = false
        if (textTitle.isNotEmpty()
            && textDescr.isNotEmpty()
            && textTime.isNotEmpty()
        ) {
            val hour = textTime.toLongOrNull()
            if (hour != null) {
                bool = true
            }
        }
        return bool
    }

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}