package com.furkanmulayim.benimsagligim.presentation.result_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentResultBinding
import com.furkanmulayim.benimsagligim.domain.model.ResultDisease

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        goingHome()
        showOnAdapter()
        return binding.root
    }


    private fun goingHome() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_resultFragment_to_diseaseDetectionFragment)
        }
    }

    private fun showOnAdapter() {
        val dataList = listOf(
            ResultDisease("Deneme", "Latince İsmi", "%70"),
            ResultDisease("A", "Latince İsmi", "%60"),
            ResultDisease("Kısalaştırılmışcasına", "Latince İsmi", "%50")
        )

        val adapter = ResultAdapter(dataList)

        binding.rcycSonuc.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycSonuc.adapter = adapter
    }


}