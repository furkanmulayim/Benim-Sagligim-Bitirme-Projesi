package com.furkanmulayim.benimsagligim.presentation.detail_future

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.fillPieChart
import com.furkanmulayim.benimsagligim.util.loadImageCategpry
import com.github.mikephil.charting.charts.PieChart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    //Room ile gelen hastalık verileri bu listeyle adaptöre göndereceğiz
    val hastalik = MutableLiveData<Disease>()

    //Room ile gelen benzer hastalık verileri bu listeyle adaptöre göndereceğiz
    val similarDiseaseList = MutableLiveData<List<Disease>>()

    fun refresh(id: Int) {
        //room ile verileri çektik bu fonksiyon frgamentten çağırılacak
        getDiseasesDataFromRoom(id)
    }

    private fun getDiseasesDataFromRoom(uuid: Int) {
        //hastakiklari ROOM'dan alıyoruz (bundle ile gelen UUID sayesinde)
        viewModelScope.launch(Dispatchers.IO) {
            val dao = DiseaseDatabase(getApplication()).diseaseDao()
            val hasta = dao.getDiseases(uuid)
            hastalik.postValue(hasta)
        }
    }

    private fun getSimilarDiseasesDataFromRoom(list: List<String>) {
        //benzer hastalıkları Room ile Çekeceğiz
        //parametre olarak gelen veri (HSV,HPV,Kolera) liste türünden
        val temp: MutableList<Disease> = arrayListOf() //geçici liste
        var x = 0
        for (i in list.indices) {
            x++
            viewModelScope.launch(Dispatchers.IO) {
                val dao = DiseaseDatabase(getApplication()).diseaseDao()
                //eğer listedeki hastalık herhangi bir hastalık ismi ile eşleşirse
                val hasta = dao.getDiseaseSimilar(list[i])
                if (hasta != null) {
                    //ve hasta değişkeni boş değilse geçici listeye ekler
                    temp.add(hasta)
                }
            }
        }
        //en son geçici listemizden asıl listemize paslarız
        similarDiseaseList.postValue(temp)
    }

    fun getSimilar(benzer: String) {
        //string olarak gelen benzer hastalıkları liste haline dönüştürür ve Room fonksiyonuna paslar.
        return getSimilarDiseasesDataFromRoom(benzer.split(",").map { it.trim() })
    }

    fun etiketAyiklaEsitle(etiketler: String): List<String> {
        //string olarak gelen benzer hastalıkları liste haline döndürür
        return etiketler.split(",").map { it.trim() }
    }

    fun gorselEsitle(image: ImageView, link: String) {
        //string olarak gelen linki image view'a indirir.
        image.loadImageCategpry(link)
    }

    fun pieChartEsitle(
        pieChartRisk: PieChart, risko: Float, pieChartGorulme: PieChart, gorulmes: Float
    ) {
        // risk oranı ve görülme oranlarını şema olarak görüntülemek için kullanıyoruz
        pieChartRisk.fillPieChart(risko, 100 - risko)
        pieChartGorulme.fillPieChart(gorulmes, 100 - gorulmes)
    }


}