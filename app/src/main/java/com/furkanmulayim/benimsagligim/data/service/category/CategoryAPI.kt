package com.furkanmulayim.benimsagligim.data.service.category

import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease
import com.furkanmulayim.benimsagligim.domain.model.Disease
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryAPI {

    @GET("furkanmulayim/Benim-Sagligim-Bitirme-Projesi/master/app/src/main/assets/benim_sagligim_disease_category.json")
    fun getCategory():Single<List<CategoryListDisease>>
}