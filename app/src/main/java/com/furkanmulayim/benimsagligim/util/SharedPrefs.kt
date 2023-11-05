package com.furkanmulayim.benimsagligim.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class SharedPrefs {

    companion object {
        private val preferencesTime = "preferences_time"
        private var sp: SharedPreferences? = null

        @Volatile
        private var instance: SharedPrefs? = null
        private val lock = Any()

        operator fun invoke(context: Context): SharedPrefs = instance ?: synchronized(lock) {

            instance ?: makeSharedPrefs(context).also {
                instance = it
            }
        }

        private fun makeSharedPrefs(context: Context): SharedPrefs {
            sp = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefs()
        }

    }

    fun saveTime(time:Long){
        sp?.edit(commit = true){
            putLong(preferencesTime,time)
        }

    }

    fun getTime() = sp?.getLong(preferencesTime,0)
}