package com.furkanmulayim.benimsagligim.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetailBinding
import com.furkanmulayim.benimsagligim.presentation.disease_detection_fragment_future.SymptomAdapter

class DiseaseDetailFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detail, container, false)
        showOnAdapter()
        return binding.root
    }


    private fun showOnAdapter() {
        val dataList = listOf(
            "Ateş",
            "Öksürük",
            "Baş Ağrısı",
            "Halsizlik",
            "Yorgunluk",
        )


        val adapter = SymptomAdapter(dataList, requireContext()) { item ->
            for (items in item) {
                println(items)
            }
        }

        binding.rcycDetailsHastags.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycDetailsHastags.adapter = adapter
    }

}