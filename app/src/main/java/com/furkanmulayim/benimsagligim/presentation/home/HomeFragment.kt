package com.furkanmulayim.benimsagligim.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        clickListeners()
    }

    private fun clickListeners() {
        //Hastalık kategorisinden itemlere tıklayınca HASTALIK DETAY sayfasına gidecek.
        binding.bulasiciHastalik.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_diseaseCategoryFragment)
        }

        //back button tıklandığında bir BANA ÖZEL SAYFASINA gidecek.
        binding.banaOzelButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_forMeFragment)
        }

        //back button tıklandığında bir NEYİM VAR SAYFASINA gidecek.
        binding.constraintLayout3.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_diseaseDetectionFragment)
        }
    }

}