package com.furkanmulayim.benimsagligim.presentation.detail_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetailBinding

class DiseaseDetailFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detail, container, false)
        backButton()
        showOnAdapter()
        return binding.root
    }
    private fun backButton() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showOnAdapter() {
        val dataList = listOf(
            "Ateş",
            "Öksürük",
            "Baş Ağrısı",
            "Halsizlik",
            "Yorgunluk",
        )
        
        val adapter = DetailSymptomAdapter(dataList)

        binding.rcycDetailsHastags.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycDetailsHastags.adapter = adapter
    }

}