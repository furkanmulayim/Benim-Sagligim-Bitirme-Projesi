package com.furkanmulayim.benimsagligim.domain.model

import com.google.gson.annotations.SerializedName

data class Disease(
    @SerializedName("Adı")
    var adi: String,
    @SerializedName("Latince Adı")
    var latinAd: String,
    @SerializedName("Hakkında")
    var hakkinda: String,
    @SerializedName("Belirti")
    var belirti: String,
    @SerializedName("Taşıdığı Risk")
    var tasidigiRisk: String,
    @SerializedName("Risk Oranı")
    var riskOrani: String,
    @SerializedName("Görülme Sıklığı")
    var gorulmeSikligi: String,
    @SerializedName("Korunma Yolları")
    var korunmaYollari: String,
    @SerializedName("Enfekte Oldum")
    var enfekteOldum: String,
    @SerializedName("Etiketler")
    var etiketler: String,
    @SerializedName("Benzer Hastalıklar")
    var benzerHastaliklar: String,
    @SerializedName("Görsel Link")
    var gorselLinki: String
)