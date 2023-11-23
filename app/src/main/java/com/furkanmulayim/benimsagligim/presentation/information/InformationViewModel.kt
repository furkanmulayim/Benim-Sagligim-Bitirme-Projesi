package com.furkanmulayim.benimsagligim.presentation.information

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.presentation.home.BaseViewModel
import com.furkanmulayim.benimsagligim.util.SharedPrefs
import com.furkanmulayim.benimsagligim.util.getGPTResponse

class InformationViewModel(application: Application) : BaseViewModel(application) {

    private var sp = SharedPrefs(getApplication())
    var textMed = MutableLiveData<String>()
    var responseMed = MutableLiveData<String>()
    //private val timeSinir = 3 *  60 * 1000 * 1000 * 1000L // 3 sn

    private val gptResponseLiveData = MutableLiveData<String>()

    fun getGPTResponseLiveData(): LiveData<String> {
        return gptResponseLiveData
    }

    fun getGPT(query: String) {
        sorguZamaniKaydet()
        getGPTResponse("Lütfen 35 karakterden oluşan $query ilacının ne işe yaradığını kısaca açıklar mısınız?") { response ->
            gptResponseLiveData.postValue(response)
        }
    }

    fun getSorgu() {
        textMed.value = sp.getImageUriInShared()
    }

    fun parsUrl(text: String): Uri {
        //Uri türünden görselimi var
        return Uri.parse(text)
    }


    fun sorguZamaniKaydet() {
        sp.saveGptTime(System.nanoTime())
    }

    /**
    fun sorguZamaniOgren(): Boolean {
    var kullanilmisMi: Boolean
    val updateTime = sp.getGptTime()
    if (updateTime != null) {
    println(" Furkan Zamana Bak Amk " + (System.nanoTime() - updateTime))
    }
    if (updateTime!= null && (System.nanoTime() - updateTime > timeSinir)){
    }
    else{
    kullanilmisMi = true
    }
    kullanilmisMi = updateTime != null && (System.nanoTime() - updateTime) > timeSinir
    return kullanilmisMi
    }

     */
    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}