package com.furkanmulayim.benimsagligim.presentation.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.data.service.CategoryAPIService
import com.furkanmulayim.benimsagligim.data.service.DiseaseAPIService
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease
import com.furkanmulayim.benimsagligim.domain.model.Disease
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val diseaseApiService = DiseaseAPIService()
    //Kullan at değişkenimiz.. Hafıza tüketmememek için
    private val disposable = CompositeDisposable()
    //hastalıkları almak için
    val diseaseList = MutableLiveData<List<Disease>>()

    private val categoryApiService = CategoryAPIService()
    //Kullan at değişkenimiz.. Hafıza tüketmememek için
    private val disp2 = CompositeDisposable()
    //hastalıkları almak için
    val categoriesList = MutableLiveData<List<CategoryListDisease>>()


    fun loadMostViews(){
        getDataFromApi()
        getCatregoriesApi()
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

    private fun getCatregoriesApi() {
        disp2.add(
            categoryApiService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CategoryListDisease>>() {
                    override fun onSuccess(t: List<CategoryListDisease>) {
                        categoriesList.value = t
                    }

                    override fun onError(e: Throwable) {
                        println("SORUN: " + e.message)
                    }
                })
        )
    }

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}