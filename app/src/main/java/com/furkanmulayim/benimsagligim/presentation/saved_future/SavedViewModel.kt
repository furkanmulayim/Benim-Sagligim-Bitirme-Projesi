package com.furkanmulayim.benimsagligim.presentation.saved_future

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.benimsagligim.data.service.saved.SavedDiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel(application: Application) : BaseViewModel(application) {

    //kaydedilen hastalıkların listesi
    val savedDiseaseList = MutableLiveData<List<Disease>>()

    private fun getDiseaseRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = SavedDiseaseDatabase(getApplication()).savedDiseaseDao()
            val hastalik = dao.getAllDiseases()
            savedDiseaseList.postValue(hastalik)
        }
    }

     fun showDiseases() {
       getDiseaseRoom()
    }

}