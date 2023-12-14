package com.furkanmulayim.benimsagligim.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CategoryListDisease(
    //column infoyu roomda saklamak için kullanacağız
    //serialized name ise gelen verinin karışmaması için jsondaki ismini kullanıyoruz
    @ColumnInfo(name = "adi") @SerializedName("Adı") var Adi: String,
    @ColumnInfo(name = "background") @SerializedName("Background") var Background: String,
    @ColumnInfo(name = "foreground") @SerializedName("Foreground") var Foreground: String
){
    //primary key room için gereklidir.
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}