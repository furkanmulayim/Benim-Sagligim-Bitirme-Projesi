package com.furkanmulayim.benimsagligim.domain.model

import com.google.gson.annotations.SerializedName

data class CategoryListDisease(

    @SerializedName("Adı")
    var Adi: String,
    @SerializedName("Background")
    var Background: String,
    @SerializedName("Foreground")
    var Foreground: String
)