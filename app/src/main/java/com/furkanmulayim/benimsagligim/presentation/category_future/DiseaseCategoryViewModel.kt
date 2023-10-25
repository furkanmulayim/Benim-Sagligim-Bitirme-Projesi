package com.furkanmulayim.benimsagligim.presentation.category_future

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.domain.model.ItemDisease


class DiseaseCategoryViewModel : ViewModel() {
    val diseaseName = MutableLiveData<String>()
    val backgroun = MutableLiveData<Int>()
    val foregroun = MutableLiveData<Int>()

    val dataList = listOf(
        ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        ), ItemDisease(
            "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
        )
    )

    fun setBundle(name: String, back: Int, fore: Int, cl: ConstraintLayout, iv: ImageView) {
        diseaseName.value = name
        backgroun.value = back
        foregroun.value = fore
        cl.setBackgroundResource(backgroun.value.toString().toInt())
        iv.setBackgroundResource(foregroun.value.toString().toInt())
    }

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}