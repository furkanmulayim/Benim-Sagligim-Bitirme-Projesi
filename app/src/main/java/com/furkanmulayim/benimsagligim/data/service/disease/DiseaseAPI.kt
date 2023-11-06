package com.furkanmulayim.benimsagligim.data.service.disease

import com.furkanmulayim.benimsagligim.domain.model.Disease
import io.reactivex.Single
import retrofit2.http.GET

interface DiseaseAPI {

    @GET("furkanmulayim/Benim-Sagligim-Bitirme-Projesi/master/app/src/main/assets/benim_sagligim_disease.json")
    fun getDiseases():Single<List<Disease>>
}