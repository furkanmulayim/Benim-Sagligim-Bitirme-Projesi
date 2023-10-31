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
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetailBinding
import com.furkanmulayim.benimsagligim.domain.model.Disease

class DiseaseDetailFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseDetailBinding
    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detail, container, false)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        observeLiveData()
        viewModel.getDataFromRoom()

        backButton()
        showOnAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    fun observeLiveData(){
        viewModel.diseaseLiveData.observe(viewLifecycleOwner, Observer {
            it.let {

                binding.hastalikAdiDetay.text = it.get(1).adi
                //binding.hakkindaDetay.text = it.hakkinda

            }
        })
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showOnAdapter() {
        val dataList = listOf(
            "Ateş",
            "Öksürük",
            "Baş Ağrısı",
            "Halsizlik",
            "Yorgunluk",
        )

        val adapter = DetailSymptomAdapter(dataList)

        binding.rcycDetailsHastags.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycDetailsHastags.adapter = adapter
    }

}