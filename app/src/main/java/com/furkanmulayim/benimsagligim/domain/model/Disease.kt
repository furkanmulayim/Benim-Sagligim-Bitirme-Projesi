package com.furkanmulayim.benimsagligim.domain.model

data class Disease(
    var adi: String,
    var latinAd: String,
    var hakkinda: String,
    var belirti: String,
    var tasidigiRisk: String,
    var riskOrani: String,
    var gorulmeSikligi: String,
    var korunmaYollari: String,
    var enfekteOldum: String,
    var etiketler: String,
    var benzerHastaliklar: String,
    var gorselLinki: String
)