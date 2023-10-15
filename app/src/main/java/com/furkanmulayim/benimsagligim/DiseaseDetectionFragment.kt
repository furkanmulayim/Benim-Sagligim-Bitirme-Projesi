package com.furkanmulayim.benimsagligim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetectionBinding

class DiseaseDetectionFragment : Fragment() {

    private lateinit var binding: FragmentDiseaseDetectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detection, container, false)
        goingHome()
        return binding.root
    }


    private fun goingHome() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_diseaseDetectionFragment_to_homeFragment)
        }
    }

}