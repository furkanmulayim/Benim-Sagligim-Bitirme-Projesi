package com.furkanmulayim.benimsagligim.presentation.detail_future

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
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetailBinding

class DiseaseDetailFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var adapter = SimilarDiseaseAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detail, container, false)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        binding.benzerRcyc.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.benzerRcyc.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        verileriAlEsitle()
        backButton()
        etiketListele()
        viewModel.getDataFromApi()
        observeLiveData()
    }

    fun verileriAlEsitle() {
        val diseaseArray = arguments?.getStringArray("diseaseBundle")
        viewModel.verileriEsle(diseaseArray)

        viewModel.gorselEsitle(binding.shapeableImageView, requireContext())

        viewModel.pieChartEsitle(binding.pieChartRisk, binding.pieChartGorulme)
    }


    private fun backButton() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun etiketListele() {
        binding.rcycDetailsHastags.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycDetailsHastags.adapter =
            viewModel.etiketAyiklaEsitle()?.let { DetailSymptomAdapter(it) }
    }

    private fun observeLiveData() {
        viewModel.diseases.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateList(it)
            }
        })
    }

}