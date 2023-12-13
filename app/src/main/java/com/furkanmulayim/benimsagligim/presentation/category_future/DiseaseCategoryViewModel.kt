package com.furkanmulayim.benimsagligim.presentation.category_future

import android.app.Application
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.categoryListeSiraBul
import com.furkanmulayim.benimsagligim.util.loadImageCategpry
import kotlinx.coroutines.launch


class DiseaseCategoryViewModel(application: Application) : BaseViewModel(application) {

    val diseaseName = MutableLiveData<String>()
    val foregroun = MutableLiveData<String>()
    val diseaseList = MutableLiveData<List<Disease>>()

    fun refresh() {
        getDiseasesDataFromRoom()
    }

    //hastakiklari ROOM'dan al
    private fun getDiseasesDataFromRoom() {
        launch {
            val dao = DiseaseDatabase(getApplication()).diseaseDao().getAllDiseases()
            showDiseases(dao)
        }
    }

    //yeni hastalıkları görüntülenecek olan listeye ekle
    private fun showDiseases(diseLis: List<Disease>) {
        var newList = diseLis.subList(diseaseBolum()[0], diseaseBolum()[1])
        diseaseList.postValue(newList)
    }

    //listeden hastalığın hangi aralıkta olduğunu bul
    private fun diseaseBolum(): Array<Int> {
        return diseaseName.value.toString().categoryListeSiraBul()
    }


    fun setBundle(name: String,fore: String, iv: ImageView) {
        diseaseName.value = name
        foregroun.value = fore
        iv.loadImageCategpry(fore)
    }

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}