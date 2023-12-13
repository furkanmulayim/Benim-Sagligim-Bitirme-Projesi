package com.furkanmulayim.benimsagligim.presentation.detection_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetectionBinding

class DiseaseDetectionFragment : Fragment() {

    private lateinit var binding: FragmentDiseaseDetectionBinding
    private var semptomList: MutableSet<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detection, container, false)
        goingHome()
        semptomList = mutableSetOf()
        showOnAdapter()
        resultPage()
        return binding.root
    }


    private fun goingHome() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_diseaseDetectionFragment_to_homeFragment)
        }
    }


    private fun showOnAdapter() {
        val dataList = listOf(
            "sefdsdg",
            "afssaf",
            "sdfsdg",
            "fgnfgn",
            "fnhnf",
        )
        val adapterGeneral = SymptomAdapter(dataList, requireContext()) { semptomList?.addAll(it) }
        binding.rcycGeneral2.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycGeneral2.adapter = adapterGeneral

        val adapterDiger = SymptomAdapter(dataList, requireContext()) { semptomList?.addAll(it) }
        binding.rcycGeneral.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycGeneral.adapter = adapterDiger
    }

    private fun resultPage() {
        binding.resultPage.setOnClickListener {
            println("----------------------------------")
            if (semptomList?.isNotEmpty() == true) {
                for (i in semptomList!!) {
                    println(i)
                }
            }

            Navigation.findNavController(requireView())
                .navigate(R.id.action_diseaseDetectionFragment_to_resultFragment)
        }
    }
}