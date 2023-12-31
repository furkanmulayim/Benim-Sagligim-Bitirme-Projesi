package com.furkanmulayim.benimsagligim.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseCategoryBinding

class DiseaseCategoryFragment : Fragment() {

    private lateinit var binding: FragmentDiseaseCategoryBinding
    private lateinit var viewModel: DiseaseCategoryViewModel
    private var adapter = DiseaseCategoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_category, container, false)
        viewModel = ViewModelProvider(this)[DiseaseCategoryViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcycCategory.layoutManager = LinearLayoutManager(requireContext())
        binding.rcycCategory.adapter = adapter

        binding.viewModel = viewModel

        viewModel.refresh()
        observeLiveData()

        incomingDataToPage()
        refreshListener()
        clickListeners()
    }

    private fun incomingDataToPage() {
        val bundle: DiseaseCategoryFragmentArgs by navArgs()

        if (bundle.diseaseName.isNotEmpty()) {
            viewModel.setBundle(
                bundle.diseaseName,
                bundle.diseaseForeground,
                binding.hastalikSvg
            )
        }
    }

    private fun refreshListener() {
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }


    private fun observeLiveData() {
        viewModel.diseaseList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateList(it)
            }
        })
    }

    private fun clickListeners() {

        //back button tıklandığında bir ANA SAYFAYA gidecek.
        binding.backButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_diseaseCategoryFragment_to_homeFragment)
        }

    }
}