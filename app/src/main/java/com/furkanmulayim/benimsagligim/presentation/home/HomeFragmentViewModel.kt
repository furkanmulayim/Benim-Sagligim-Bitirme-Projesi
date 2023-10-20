package com.furkanmulayim.benimsagligim.presentation.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease

class HomeFragmentViewModel : ViewModel() {

    val categoryList = listOf(
        CategoryListDisease(
            "Bulaşıcı", R.drawable.kategori_bulasici_back, R.drawable.kategori_bulasici_on
        ),
        CategoryListDisease(
            "Nörolojik", R.drawable.kategori_norolojik_back, R.drawable.kategori_norolojik_on
        ),
        CategoryListDisease(
            "Solunum", R.drawable.kategori_solunum_back, R.drawable.kategori_solunum_on
        ),
        CategoryListDisease(
            "Mental", R.drawable.kategori_mental_back, R.drawable.kategori_mental_on
        ),
        CategoryListDisease(
            "Üreme", R.drawable.kategori_ureme_back, R.drawable.kategori_ureme_on
        ),
        CategoryListDisease(
            "Cinsel", R.drawable.kategori_cinsel_back, R.drawable.kategori_cinsel_on
        ),
        CategoryListDisease(
            "Sindirim", R.drawable.kategori_sindirim_back, R.drawable.kategori_sindirim_on
        ),
        CategoryListDisease(
            "Kas-İskelet", R.drawable.kategori_kas_iskelet_back, R.drawable.kategori_kas_on
        ),
    )

    fun navigate(view: View, pageId: Int) {
        Navigation.findNavController(view).navigate(pageId)
    }
}