package com.furkanmulayim.benimsagligim.presentation.result_future

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentResultBinding
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.presentation.detail_future.DetailSymptomAdapter

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel
    private var adapterHastags = DetailSymptomAdapter(arrayListOf())
    private var adapterResult = ResultAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        viewModel = ViewModelProvider(this)[ResultViewModel::class.java]
        incomingData()
        goingHome()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        showOnAdapter()
    }

    private fun incomingData() {
        val mergedList = arguments?.getStringArrayList("mergedList")
        if (!mergedList.isNullOrEmpty()) {
            viewModel.gelenListe(mergedList)
            adapterHastags.updateList(mergedList)
            binding.rcycGen.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.rcycGen.adapter = adapterHastags
        }
    }

    private fun goingHome() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_resultFragment_to_diseaseDetectionFragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showOnAdapter() {
        viewModel.hastalikList.observe(viewLifecycleOwner, Observer { diseaseList ->
            if (diseaseList.isNullOrEmpty()) {
                listeBosIse()
            }
            // Eski verileri temizle
            adapterResult.clearList()
            viewModel.removeDuplicateDiseases()
            adapterResult.updateList(diseaseList as ArrayList<Disease>)
            binding.rcycSonuc.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcycSonuc.adapter = adapterResult
        })
    }

    fun listeBosIse() {
        binding.aciklama.text = getString(R.string.bir_sorun_var_sonuc)
    }


}