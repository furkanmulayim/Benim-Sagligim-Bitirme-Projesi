package com.furkanmulayim.benimsagligim.presentation.result_future

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDAO
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultViewModel(application: Application) : BaseViewModel(application) {

    private val diseaseDao: DiseaseDAO = DiseaseDatabase(getApplication()).diseaseDao()
    private val diseaseList = MutableLiveData<List<Disease>>()
    val hastalikList = MutableLiveData<List<Disease>>()
    var eslesenId: MutableList<Int> = mutableListOf()
    var temp: MutableList<Disease> = mutableListOf()

    private fun getDataFromRoom(list: ArrayList<String>) {
        launch {
            val disease = diseaseDao.getAllDiseases()
            diseaseList.postValue(disease)
            findSimilarDisease(list, disease)
        }
    }

    fun gelenListe(list: ArrayList<String>) {
        getDataFromRoom(list)

    }


    private fun findSimilarDisease(etiketList: List<String>, dl: List<Disease>) {
        val threshold = 0.50 // %50 benzerlik eşiği
        for (i in dl) {
            val diseaseEtiketi = i.etiketler.split(",").map { it.trim() }
            val yuzde = benzerlikHesapla(etiketList, diseaseEtiketi)
            if (yuzde >= threshold) {
                //eslesenId.add(i.uuid)
                temp.add(i)
            }
        }
        hastalikList.postValue(temp)
        //getDiseasesDataFromRoom(eslesenId)
    }


    private fun getDiseasesDataFromRoom(eslesenId: MutableList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            val dao = DiseaseDatabase(getApplication()).diseaseDao()
            for (i in eslesenId) {
                val hasta = dao.getDiseases(i)
                temp.add(hasta)
            }
            hastalikList.postValue(temp)
        }
    }

    fun removeDuplicateDiseases() {
        val currentList = hastalikList.value ?: return
        val uniqueSet = HashSet<Disease>()
        val uniqueList = currentList.filter { uniqueSet.add(it) }
        if (currentList.size != uniqueList.size) {
            hastalikList.value = uniqueList
        }
    }


    fun benzerlikHesapla(liste1: List<String>, liste2: List<String>): Double {
        // Boş listeler varsa benzerlik sıfırdır.
        if (liste1.isEmpty() || liste2.isEmpty()) {
            return 0.0
        }

        // İki listedeki ortak öğeleri bul.
        val ortakElemanlar = liste1.intersect(liste2.toSet()).toList()

        // Benzerlik oranını hesapla ve döndür.
        return ortakElemanlar.size.toDouble() / liste1.size.toDouble()
    }
}