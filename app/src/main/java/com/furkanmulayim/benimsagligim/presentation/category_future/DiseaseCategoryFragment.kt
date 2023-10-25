package com.furkanmulayim.benimsagligim.presentation.category_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseCategoryBinding

class DiseaseCategoryFragment : Fragment() {

    private lateinit var binding: FragmentDiseaseCategoryBinding
    private lateinit var viewModel: DiseaseCategoryViewModel

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
        binding.viewModel = viewModel
        showOnAdapter()
        incomingDataToPage()
        clickListeners()
    }

    private fun incomingDataToPage() {
        val bundle: DiseaseCategoryFragmentArgs by navArgs()

        if (bundle.diseaseName.isNotEmpty()){
            viewModel.setBundle(
                bundle.diseaseName,
                bundle.diseaseBackground,
                bundle.diseaseForeground,
                binding.diseaseNameSpace,
                binding.hastalikSvg
            )
        }

    }

    private fun showOnAdapter() {
        binding.rcycCategory.layoutManager = LinearLayoutManager(requireContext())
        binding.rcycCategory.adapter = DiseaseCategoryAdapter(viewModel.dataList)
    }

    private fun clickListeners() {

        //back button tıklandığında bir ANA SAYFAYA gidecek.
        binding.backButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_diseaseCategoryFragment_to_homeFragment)
        }

        //hastalık button tıklandığında bir DETAY SAYFASINA gidecek.
        binding.diseaseNameSpace.setOnClickListener {
            viewModel.navigate(
                requireView(), R.id.action_diseaseCategoryFragment_to_diseaseDetailFragment
            )
        }
    }
}