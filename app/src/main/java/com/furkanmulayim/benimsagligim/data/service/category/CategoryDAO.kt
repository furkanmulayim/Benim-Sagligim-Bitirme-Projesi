package com.furkanmulayim.benimsagligim.data.service.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease

@Dao
interface CategoryDAO {
    //Data Access Object

    //suspend -> coroutine içerisinde çağırılır ve durdurulup başlatılabilir.
    // vararg -> bir model objeyi farklı sayılarda alacağız sayısını bilmiyoruz
    // list long ise primary keyi döndürür int olduğu için long türünden..

    @Insert
    fun insertAll(vararg categorie: CategoryListDisease): Array<Long>

    //Roomda saklarken model sınıf ismiyle kaydolduğu için model ismi ile giriyoruz.
    @Query("SELECT * FROM categorylistdisease")
    suspend fun getAllCategory(): List<CategoryListDisease>

    //detay sayfası için tek veri döndürür, parametre olarak primary key alır
    @Query("SELECT * FROM categorylistdisease WHERE uuid = :categoryId")
    suspend fun getCategory(categoryId: Int): CategoryListDisease

    //kullanıcı verileri temizlemesi için
    @Query("DELETE FROM categorylistdisease")
    fun deleteAllCategory()

}