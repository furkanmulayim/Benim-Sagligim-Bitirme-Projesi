package com.furkanmulayim.benimsagligim.presentation.detection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetectionBinding
import com.furkanmulayim.benimsagligim.util.showMessage

class DiseaseDetectionFragment : Fragment() {

    private lateinit var binding: FragmentDiseaseDetectionBinding
    private lateinit var viewModel: DetectionViewModel
    private var symptomLists: MutableMap<Char, MutableSet<String>> = mutableMapOf(
        'a' to mutableSetOf(),
        'b' to mutableSetOf(),
        'c' to mutableSetOf(),
        'd' to mutableSetOf(),
        'e' to mutableSetOf(),
        'f' to mutableSetOf(),
        'g' to mutableSetOf(),
        'h' to mutableSetOf()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detection, container, false)
        viewModel = ViewModelProvider(this)[DetectionViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setupUI()
        observeEvents()
    }

    private fun setupUI() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(
                    R.id.action_diseaseDetectionFragment_to_homeFragment
                )
        }

        val adapters = mapOf(
            binding.rcycGen to 'a',
            binding.rcycDig to 'b',
            binding.rcycSol to 'c',
            binding.rcycNor to 'd',
            binding.rcycBil to 'e',
            binding.rcycKas to 'f',
            binding.rcycBos to 'g',
            binding.rcycGeni to 'h'
        )

        adapters.forEach { (rcyc, category) ->
            initSymptomAdapter(rcyc, category)
        }
    }

    private fun initSymptomAdapter(rcyc: RecyclerView, category: Char) {
        val adapter = SymptomAdapter(
            viewModel.getList(category, requireParentFragment()), requireContext()
        ) {
            symptomLists[category]?.apply {
                clear()
                addAll(it)
            }
        }

        rcyc.layoutManager = GridLayoutManager(requireContext(), 3)
        rcyc.adapter = adapter
    }

    private fun observeEvents() {
        binding.resultPage.setOnClickListener {
            mergeListsAndNavigate()
        }
    }

    private fun mergeListsAndNavigate() {
        val mergedSet = symptomLists.values.flatten().toSet()

        if (mergedSet.isEmpty()) {
            handleEmptyCategory()
        } else {
            val bundle = Bundle().apply {
                putStringArrayList("mergedList", ArrayList(mergedSet))
            }
            navigateToResultFragment(bundle)
        }
    }

    private fun handleEmptyCategory() {
        requireActivity().showMessage(getString(R.string.semptom_bos))
    }

    private fun navigateToResultFragment(bundle: Bundle) {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_diseaseDetectionFragment_to_resultFragment, bundle)
    }




}
