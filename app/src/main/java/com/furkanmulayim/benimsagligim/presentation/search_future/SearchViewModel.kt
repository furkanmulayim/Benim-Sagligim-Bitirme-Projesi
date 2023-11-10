package com.furkanmulayim.benimsagligim.presentation.search_future

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDAO
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : BaseViewModel(application) {

    private val diseaseDao : DiseaseDAO = DiseaseDatabase(getApplication()).diseaseDao()
    val allDiseaseList = MutableLiveData<List<Disease>>()

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }

    fun refresh(){
        //init için roomdan verileri alır
        getDiseaseRoom()
    }

    fun showDisease(disease:List<Disease>){
        //Parametre olarak gelen hastalıkları değişkende saklar
        allDiseaseList.postValue(disease)
    }

    private fun getDiseaseRoom(){
        //Tüm Hastalıkları Getirir
        launch {
            val diseases = diseaseDao.getAllDiseases()
            showDisease(diseases)
        }
    }

}