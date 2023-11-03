package com.furkanmulayim.benimsagligim.presentation.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.data.service.DiseaseAPIService
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.domain.model.ResultDisease
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val diseaseApiService = DiseaseAPIService()

    //Kullan at değişkenimiz.. Hafıza tüketmememek için
    private val disposable = CompositeDisposable()
    val diseaseList = MutableLiveData<List<Disease>>()

    val categoryList = listOf(
        CategoryListDisease(
            "Bulaşıcı Hastalıklar",
            R.drawable.kategori_bulasici_back,
            R.drawable.kategori_bulasici_on
        ), CategoryListDisease(
            "Nörolojik Hastalıklar",
            R.drawable.kategori_norolojik_back,
            R.drawable.kategori_norolojik_on
        ), CategoryListDisease(
            "Solunum Sistemi Hastalıkları",
            R.drawable.kategori_solunum_back,
            R.drawable.kategori_solunum_on
        ), CategoryListDisease(
            "Ruhsal Hastalıklar", R.drawable.kategori_mental_back, R.drawable.kategori_mental_on
        ), CategoryListDisease(
            "Üreme Sistemi Hastalıkları",
            R.drawable.kategori_ureme_back,
            R.drawable.kategori_ureme_on
        ), CategoryListDisease(
            "Cinsel Yolla Bulaşan Hastalıklar",
            R.drawable.kategori_cinsel_back,
            R.drawable.kategori_cinsel_on
        ), CategoryListDisease(
            "Sindirim Sis. Hastalıkları",
            R.drawable.kategori_sindirim_back,
            R.drawable.kategori_sindirim_on
        ), CategoryListDisease(
            "Kas-İskelet Sis. Hastalıkları",
            R.drawable.kategori_kas_iskelet_back,
            R.drawable.kategori_kas_on
        )
    )

    fun loadMostViews(){
        getDataFromApi()
    }

    private fun getDataFromApi() {
        disposable.add(
            diseaseApiService.getData()
                //Async Olarak Yeni threadde yapar
                .subscribeOn(Schedulers.newThread())
                //Ana Threadde göstereceğiz
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Disease>>() {
                    override fun onSuccess(t: List<Disease>) {
                        diseaseList.value = t
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        )
    }

    val denemeList = listOf(
        ResultDisease("Deneme 1. Hastalık", "bos", "bos"),
        ResultDisease("Deneme 2. Hastalık", "bos", "bos"),
        ResultDisease("Deneme 3. Hastalık", "bos", "bos"),
        ResultDisease("Deneme 4. Hastalık", "bos", "bos")
    )

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}