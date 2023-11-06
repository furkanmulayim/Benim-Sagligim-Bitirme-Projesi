package com.furkanmulayim.benimsagligim.data.service.disease

import com.furkanmulayim.benimsagligim.domain.model.Disease
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiseaseAPIService {

    //https://raw.githubusercontent.com/furkanmulayim/Benim-Sagligim-Bitirme-Projesi/master/app/src/main/assets/benim_sagligim_disease.json
    private val baseUrl = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder().baseUrl(baseUrl)
        //
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        .create(DiseaseAPI::class.java)

    fun getData(): Single<List<Disease>>{
        return api.getDiseases()
    }
}