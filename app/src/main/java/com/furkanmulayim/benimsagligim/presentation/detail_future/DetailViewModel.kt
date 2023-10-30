package com.furkanmulayim.benimsagligim.presentation.detail_future

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanmulayim.benimsagligim.data.service.DiseaseAPIService
import com.furkanmulayim.benimsagligim.domain.model.Disease
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {

    private val diseaseApiService = DiseaseAPIService()

    //Kullan at değişkenimiz.. Hafıza tüketmememek için
    private val disposable = CompositeDisposable()


    val diseaseLiveData = MutableLiveData<List<Disease>>()

    fun getDataFromRoom() {
        getDataFromApi()
    }

    fun getDataFromApi() {
        disposable.add(
            diseaseApiService.getData()
                //Async Olarak Yeni threadde yapar
                .subscribeOn(Schedulers.newThread())
                //Ana Threadde göstereceğiz
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Disease>>() {
                    override fun onSuccess(t: List<Disease>) {
                        diseaseLiveData.value = t
                    }

                    override fun onError(e: Throwable) {

                        println("furkaaaan" + e.localizedMessage)
                    }

                })


        )
    }

}