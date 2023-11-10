package com.furkanmulayim.benimsagligim.presentation.home

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.data.service.category.CategoryAPIService
import com.furkanmulayim.benimsagligim.data.service.category.CategoryDAO
import com.furkanmulayim.benimsagligim.data.service.category.CategoryDatabase
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseAPIService
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDAO
import com.furkanmulayim.benimsagligim.data.service.disease.DiseaseDatabase
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.util.SharedPrefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    //shared preferences nesnesi
    private var sp = SharedPrefs(getApplication())

    //api nesneleri
    private val diseaseApiService = DiseaseAPIService()
    private val categoryApiService = CategoryAPIService()

    //room nesneleri
    private val diseaseDao: DiseaseDAO = DiseaseDatabase(getApplication()).diseaseDao()
    private val categoryDao: CategoryDAO = CategoryDatabase(getApplication()).categoryDao()

    private val disposable = CompositeDisposable()//Kullan at değişkenimiz..

    val diseaseList = MutableLiveData<List<Disease>>()//hastalıkları almak için
    val categoriesList = MutableLiveData<List<CategoryListDisease>>()//kategorileri almak için

    fun refreshData() {
        val updateTime = sp.getTime()

        if (updateTime != null && updateTime != 0L) {
            getDiseaseDataFromSqlite()
            getCategoryFromSQLite()
        } else {
            getDataFromApi()
            getCatregoriesApi()
        }
    }

    /**======================================HASTALIKLAR========================================*/
    //apiden hastalık verileri alır
    private fun getDataFromApi() {
        disposable.add(
            diseaseApiService.getData()
                //Async Olarak Yeni threadde yapar
                .subscribeOn(Schedulers.newThread())
                //Ana Threadde göstereceğiz
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Disease>>() {
                    override fun onSuccess(t: List<Disease>) {
                        diseaseDepolaSQLite(t)//Apiden gelen listeyi depoya gönderik
                        showDiseases(t) //Apiden gelen listeyi döndürdük
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )
    }


    private fun diseaseDepolaSQLite(list: List<Disease>) {
        //hastalık verilerini Room ile sqlde depolar
        viewModelScope.launch(Dispatchers.IO) {
            diseaseDao.deleteAlldisease()
            val listLong = diseaseDao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i++
            }
        }
        //kaydedilen zamanı alıyoruz
        sp.saveTime(System.nanoTime())
    }

    private fun getDiseaseDataFromSqlite() {
        launch {
            val disease = diseaseDao.getAllDiseases()
            showDiseases(disease.shuffled())
        }
    }

    private fun showDiseases(diseLis: List<Disease>) {
        diseaseList.postValue(diseLis)
    }

    /**======================================KATEGORİLER========================================*/
    //kategorileri apiden getirir getirir
    private fun getCatregoriesApi() {
        disposable.add(
            categoryApiService.getCategories().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CategoryListDisease>>() {
                    override fun onSuccess(t: List<CategoryListDisease>) {
                        categoryDepolaSQLite(t)
                        showCategory(t)
                    }

                    override fun onError(e: Throwable) {
                        println("SORUN: " + e.message)
                    }
                })
        )
    }

    private fun categoryDepolaSQLite(list: List<CategoryListDisease>) {

        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.deleteAllCategory()
            val listLong = categoryDao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i++
            }
        }
    }

    private fun getCategoryFromSQLite() {
        launch {
            val categories = CategoryDatabase(getApplication()).categoryDao().getAllCategory()
            showCategory(categories)
        }
    }

    private fun showCategory(catList: List<CategoryListDisease>) {
        categoriesList.postValue(catList)
    }

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }


}