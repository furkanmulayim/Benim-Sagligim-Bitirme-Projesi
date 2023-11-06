package com.furkanmulayim.benimsagligim.data.service.category

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease

/**
 * Database sınıfı birden fazla modeli yazmak isteyebilir o yüzden bizden
 * dizi istiyor mutlaka arrayOf içine modelimizi giriyoruz.
 *
 * Not: Room için versiyon değiştirmeye gerek yoktur. sadece veritabanı
 * değişikliği olduğu zaman versiyon değiştirilmelidir.
 *
 * Database sınıfı abstract olmaloıdır.
 *
 * */

@Database(entities = [CategoryListDisease::class], version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDAO

    /**
     * Database için farklı threadlerden conflictic olmasın diye
     * SİNGELTON mantığı ile oluşturacağız.
     * */

    companion object {
        // farklı scopelerden ulaşabiliriz
        @Volatile //farklı threadlerden çalıştırabilmek için "Volatile"
        private var instance: CategoryDatabase? = null

        private val lock = Any()

        /**
         * instance var mı yok mu kontrol et ve senkronize bir şekilde yap.
         * bir threadin işi bitince sıradakine geçer.
         * */
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                //instance oluşturarak database'e eşitliyoruz
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CategoryDatabase::class.java, "categorydatabase"
        ).build()
    }
}