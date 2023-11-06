package com.furkanmulayim.benimsagligim.data.service.category

import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoryAPIService {
    private val baseUrlCategory = "https://raw.githubusercontent.com/"
    private val apii = Retrofit.Builder().baseUrl(baseUrlCategory)
        //
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        .create(CategoryAPI::class.java)

    fun getCategories(): Single<List<CategoryListDisease>>{
        return apii.getCategory()
    }
}