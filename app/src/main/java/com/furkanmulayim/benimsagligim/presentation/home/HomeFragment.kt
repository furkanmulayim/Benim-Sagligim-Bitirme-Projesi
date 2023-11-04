package com.furkanmulayim.benimsagligim.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentHomeBinding
import com.furkanmulayim.benimsagligim.util.startCallWithPermission

class HomeFragment : Fragment() {

    //ui bilesenlerim
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    //adaptör nesnelerim
    private var adapterDisease = MostViewsAdapter(arrayListOf())
    private var adapterCategories = CategoryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.mostViewsDisease.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.mostViewsDisease.adapter = adapterDisease

        binding.categoryRcyc.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.categoryRcyc.adapter = adapterCategories

        viewModel.loadMostViews()
        observeLiveData()
        clickListeners()
    }


    private fun observeLiveData() {
        viewModel.diseaseList.observe(viewLifecycleOwner, Observer {
            adapterDisease.updateList(it)
        })

        viewModel.categoriesList.observe(viewLifecycleOwner, Observer {
            adapterCategories.updateList(it)
        })
    }


    private fun clickListeners() {

        //back button tıklandığında BANA ÖZEL SAYFASINA gidecek.
        binding.banaOzelButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_forMeFragment)
        }

        //back button tıklandığında NEYİM VAR SAYFASINA gidecek.
        binding.constraintLayout3.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_diseaseDetectionFragment)
        }

        //search button tıklanınca ARAMA SAYFASINA gidecek.
        binding.searchDiseaseButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_searchFragment)
        }

        binding.emergencyCall.setOnClickListener {
            requireActivity().startCallWithPermission("5344533008", 123)
        }

        binding.pillDetectButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_pillInformationFragment)
        }

    }
}