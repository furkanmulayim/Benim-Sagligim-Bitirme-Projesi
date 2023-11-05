package com.furkanmulayim.benimsagligim.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Disease(
    //column infoyu roomda saklamak için kullanacağız
    //serialized name ise gelen verinin karışmaması için jsondaki ismini kullanıyoruz
    @ColumnInfo(name = "adi") @SerializedName("Adı") var adi: String,
    @ColumnInfo(name = "latince") @SerializedName("Latince Adı") var latinAd: String,
    @ColumnInfo(name = "hakkinda") @SerializedName("Hakkında") var hakkinda: String,
    @ColumnInfo(name = "risk") @SerializedName("Risk Oranı") var riskOrani: String,
    @ColumnInfo(name = "gorulme") @SerializedName("Görülme Sıklığı") var gorulmeSikligi: String,
    @ColumnInfo(name = "korunma") @SerializedName("Korunma Yolları") var korunmaYollari: String,
    @ColumnInfo(name = "enfekte") @SerializedName("Enfekte Oldum") var enfekteOldum: String,
    @ColumnInfo(name = "etiket") @SerializedName("Etiketler") var etiketler: String,
    @ColumnInfo(name = "benzer") @SerializedName("Benzer Hastalıklar") var benzerHastaliklar: String,
    @ColumnInfo(name = "link") @SerializedName("Görsel Link") var gorselLinki: String
) {
    //primary key room için gereklidir.
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}