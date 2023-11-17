package com.furkanmulayim.benimsagligim.presentation.search_future

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDAO
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.SharedPrefs
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : BaseViewModel(application) {

    private val diseaseDao: DiseaseDAO = DiseaseDatabase(getApplication()).diseaseDao()
    val allDiseaseList = MutableLiveData<List<Disease>>()
    val seciliHasta = MutableLiveData<List<Disease>>()

    fun navigate(view: View, pageId: Int) {
        //Sayfa Geçişileri için kullanılan method
        Navigation.findNavController(view).navigate(pageId)
    }

    fun refresh() {
        //init için roomdan verileri alır
        getDiseaseRoom()
    }

    private fun getDiseaseRoom() {
        //Tüm Hastalıkları Getirir
        launch {
            val diseases = diseaseDao.getAllDiseases()
            showDisease(diseases)
        }
    }

    fun showDisease(disease: List<Disease>) {
        //Parametre olarak gelen hastalıkları değişkende saklar
        allDiseaseList.postValue(disease)
    }

    fun searchControl(editText: EditText, observeArananHastalik: Unit) {
        //arama sorgusunu yönetecek fonksiyon her harfe basılınca tetiklenir
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                //listeleme fonksiyonuna gönderiyoruz
                arananHastalikListele(s.toString())
                return observeArananHastalik
            }
        })
    }

    fun arananHastalikListele(s: String) {
        println("arguments view modelde : $s")
        //veri geldiğinde sorgu yapacağız. ve listeye ekleyeceğiz
        allDiseaseList.value?.let { diseaseList ->
            val filteredDiseases = diseaseList.filter { it.adi.contains(s, true) }
            seciliHasta.postValue(filteredDiseases)
        }
    }
}
