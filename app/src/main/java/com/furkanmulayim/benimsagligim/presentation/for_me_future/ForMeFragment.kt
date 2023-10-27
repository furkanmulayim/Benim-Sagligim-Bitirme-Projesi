package com.furkanmulayim.benimsagligim.presentation.for_me_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentForMeBinding
import com.furkanmulayim.benimsagligim.domain.model.ItemDisease

class ForMeFragment : Fragment() {

    private lateinit var binding: FragmentForMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_for_me, container, false)
        showOnAdapter()
        clickListeners()
        return binding.root
    }

    private fun clickListeners() {
        binding.hatirlaticim.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_forMeFragment_to_notificationFragment)
        }

        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_forMeFragment_to_homeFragment)
        }
    }

    private fun showOnAdapter() {
        val dataList = listOf(
            ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ),
            ItemDisease(
                "Hastalık", "Latince İsmi", "Etiket1, Etiket2, Etiket3", "Derecelendirmesi"
            ),
        )

        val adapter = ForMeAdapter(dataList)

        binding.savedDiseases.layoutManager = LinearLayoutManager(requireContext())
        binding.savedDiseases.adapter = adapter
    }

}