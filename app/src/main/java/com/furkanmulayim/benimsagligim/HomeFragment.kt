package com.furkanmulayim.benimsagligim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        goingDiseaseDetailPage()
        goingForMePage()
        goingDetectionPage()
        return binding.root
    }

    private fun goingDiseaseDetailPage() {
        binding.bulasiciHastalik.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_diseaseCategoryFragment)
        }
    }

    private fun goingForMePage() {
        binding.banaOzelButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_forMeFragment)
        }
    }

    private fun goingDetectionPage() {
        binding.constraintLayout3.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_diseaseDetectionFragment)
        }
    }

}