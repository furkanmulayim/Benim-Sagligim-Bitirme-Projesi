package com.furkanmulayim.benimsagligim.presentation.category_future

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.data.service.DiseaseAPIService
import com.furkanmulayim.benimsagligim.domain.model.Disease
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class DiseaseCategoryViewModel : ViewModel() {

    private val diseaseApiService = DiseaseAPIService()

    //Kullan at değişkenimiz.. Hafıza tüketmememek için
    private val disposable = CompositeDisposable()

    val diseaseName = MutableLiveData<String>()
    val backgroun = MutableLiveData<Int>()
    val foregroun = MutableLiveData<Int>()

    val diseaseList = MutableLiveData<List<Disease>>()

    fun refresh() {
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

                        diseaseList.value = t.subList(hangisindeyik()[0], hangisindeyik()[1])
                    }

                    override fun onError(e: Throwable) {
                        println("furkaaaan" + e.localizedMessage)
                    }
                })
        )
    }

    private fun hangisindeyik(): Array<Int> {
        var a = Array<Int>(2) { 0 }
        if (diseaseName.value.toString().equals("Bulaşıcı Hastalıklar")) {
            a[0] = 0
            a[1] = 14
        }

        if (diseaseName.value.toString().equals("Nörolojik Hastalıklar")) {
            a[0] = 1
            a[1] = 3
        }
        return a
    }


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