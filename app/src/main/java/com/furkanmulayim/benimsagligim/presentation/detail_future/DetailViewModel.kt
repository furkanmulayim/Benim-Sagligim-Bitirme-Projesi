package com.furkanmulayim.benimsagligim.presentation.detail_future

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.benimsagligim.domain.model.Disease

class DetailViewModel : ViewModel() {

    val diseaseLiveData = MutableLiveData<Disease>()

    fun getDataFromRoom() {
        val disease = Disease(
            "Adı",
            "Latin Adı",
            "Hakkında",
            "Belirti",
            "tasdisigi risk",
            "risk oranı",
            "gorulme sıklıgı",
            "korunma yooları",
            "enfekte oldum",
            "etiket,etiket,etiket",
            "deneme,deneme,deneme",
            "www.link.com",
        )

        diseaseLiveData.value = disease
    }

}