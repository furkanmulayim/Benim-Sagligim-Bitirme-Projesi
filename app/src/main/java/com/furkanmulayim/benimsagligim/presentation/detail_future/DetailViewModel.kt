package com.furkanmulayim.benimsagligim.presentation.detail_future

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.benimsagligim.data.service.DiseaseAPIService
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.util.ProgressBarr
import com.furkanmulayim.benimsagligim.util.fillPieChart
import com.furkanmulayim.benimsagligim.util.loadImage
import com.github.mikephil.charting.charts.PieChart
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {

    //Kullan at değişkenimiz.. Hafıza tüketmememek için
    private val disposable = CompositeDisposable()
    private val diseaseApiService = DiseaseAPIService()


    private var list: Array<String>? = null
    val diseases = MutableLiveData<List<Disease>>()

    val adi = MutableLiveData<String>()
    val latinAd = MutableLiveData<String>()
    val hakkinda = MutableLiveData<String>()
    val riskOrani = MutableLiveData<String>()
    val gorulmeSikligi = MutableLiveData<String>()
    val riskText = MutableLiveData<String>()
    val gorulmeText = MutableLiveData<String>()
    val korunmaYollari = MutableLiveData<String>()
    val enfekteOldum = MutableLiveData<String>()
    val etiketler = MutableLiveData<String>()
    val benzerHastaliklar = MutableLiveData<String>()

    fun getDataFromApi() {
        disposable.add(
            diseaseApiService.getData()
                //Async Olarak Yeni threadde yapar
                .subscribeOn(Schedulers.newThread())
                //Ana Threadde göstereceğiz
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Disease>>() {
                    override fun onSuccess(t: List<Disease>) {

                        val listemiz = benzerHastaliklar.value?.let { benzerListAyikla(it) }
                        val temp = mutableListOf<Disease>()

                        for (i in t) {
                            if (listemiz != null) {
                                for (x in listemiz) {
                                    if (x == i.adi) {
                                        temp.add(i)
                                    }
                                }
                            }
                        }
                        diseases.value = temp

                    }

                    override fun onError(e: Throwable) {
                        println("furkaaaan" + e.localizedMessage)
                    }
                })
        )
    }


    //bundle array ile gelen veriyi eşitliyoruz
    fun verileriEsle(diseaseArray: Array<String>?) {
        list = diseaseArray
        list.let {
            if (it != null) {
                adi.value = it[0]
                latinAd.value = it[1]
                hakkinda.value = it[2]
                riskOrani.value = it[3]
                gorulmeSikligi.value = it[4]
                korunmaYollari.value = it[5]
                enfekteOldum.value = it[6]
                etiketler.value = it[7]
                benzerHastaliklar.value = it[8]

                riskText.value = "%" + it[3]
                gorulmeText.value = "%" + it[4]
            }
        }
    }

    fun gorselEsitle(image: ImageView, context: Context) {
        image.loadImage(list?.get(9), ProgressBarr(context))
    }

    fun pieChartEsitle(pieChartRisk: PieChart, pieChartGorulme: PieChart) {
        val riskOranSayisi = riskOrani.value?.toFloat()
        val gorulmeSiklikSayisi = gorulmeSikligi.value?.toFloat()

        if (riskOranSayisi != null) {
            pieChartRisk.fillPieChart(riskOranSayisi, (100 - riskOranSayisi))
        }

        if (gorulmeSiklikSayisi != null) {
            pieChartGorulme.fillPieChart(gorulmeSiklikSayisi, (100 - gorulmeSiklikSayisi))
        }
    }

    fun etiketAyiklaEsitle(): List<String>? {
        return etiketler.value?.split(",")?.map { it.trim() }
    }

    fun benzerListAyikla(s: String): List<String> {
        return s.split(",").map { it.trim() }
    }


}