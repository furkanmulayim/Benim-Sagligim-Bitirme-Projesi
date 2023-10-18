package com.furkanmulayim.benimsagligim.presentation.disease_category_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseCategoryBinding
import com.furkanmulayim.benimsagligim.domain.model.ItemDisease

class DiseaseCategoryFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_category, container, false)
        backButton()
        goingDetails()
        showOnAdapter()
        return binding.root
    }

    private fun showOnAdapter() {
        val dataList = listOf(
            ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ),
            ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ), ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            )
        )

        val adapter = DiseaseCategoryAdapter(dataList)

        binding.rcycCategory.layoutManager = LinearLayoutManager(requireContext())
        binding.rcycCategory.adapter = adapter
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_diseaseCategoryFragment_to_homeFragment)
        }
    }

    private fun goingDetails(){
        binding.diseaseNameSpace.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_diseaseCategoryFragment_to_diseaseDetailFragment)
        }
    }

}