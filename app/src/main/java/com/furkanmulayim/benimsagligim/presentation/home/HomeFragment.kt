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

        //adaptör ayarlama
        binding.mostViewsDisease.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.mostViewsDisease.adapter = adapterDisease

        //adaptör ayarlama
        binding.categoryRcyc.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.categoryRcyc.adapter = adapterCategories

        //metotları görünüm oluşturulduktan sonra çağırmak
        viewModel.refreshData()
        observeLiveData()
        clickListeners()
    }


    private fun observeLiveData() {
        viewModel.diseaseList.observe(viewLifecycleOwner, Observer {
            //viewmodelden gelen hastalık verilerini ile adaptörü güncelliyoruz
            it?.let {
                adapterDisease.updateList(it)
            }
        })

        viewModel.categoriesList.observe(viewLifecycleOwner, Observer {
            //viewmodelden gelen kategori verilerini ile adaptörü güncelliyoruz
            it?.let {
                adapterCategories.updateList(it)
            }
        })
    }


    private fun clickListeners() {

        //bana özele
        binding.banaOzelButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_forMeFragment)
        }

        //neyim var
        binding.constraintLayout3.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_diseaseDetectionFragment)
        }

        //search page
        binding.searchDiseaseButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_searchFragment)
        }

        //acila arama
        binding.emergencyCall.setOnClickListener {
            requireActivity().startCallWithPermission("5344533008", 123)
        }

        //ilaç tara
        binding.pillDetectButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_homeFragment_to_pillInformationFragment)
        }

    }
}