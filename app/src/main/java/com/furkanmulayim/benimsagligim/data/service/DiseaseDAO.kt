package com.furkanmulayim.benimsagligim.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.benimsagligim.domain.model.Disease

@Dao
interface DiseaseDAO {
    //Data Access Object

    //suspend -> coroutine içerisinde çağırılır ve durdurulup başlatılabilir.
    // vararg -> bir model objeyi farklı sayılarda alacağız sayısını bilmiyoruz
    // list long ise primary keyi döndürür int olduğu için long türünden..

    @Insert
    fun insertAll(vararg disease: Disease): Array<Long>

    //Roomda saklarken model sınıf ismiyle kaydolduğu için model ismi ile giriyoruz.
    @Query("SELECT * FROM disease")
    suspend fun getAllDiseases(): List<Disease>

    //detay sayfası için tek veri döndürür, parametre olarak primary key alır
    @Query("SELECT * FROM disease WHERE uuid = :diseaseId")
    suspend fun getDiseases(diseaseId: Int): Disease

    //kullanıcı verileri temizlemesi için
    @Query("DELETE FROM disease")
    fun deleteAlldisease()

}