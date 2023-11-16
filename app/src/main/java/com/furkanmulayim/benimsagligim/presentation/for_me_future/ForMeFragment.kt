package com.furkanmulayim.benimsagligim.presentation.for_me_future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentForMeBinding
import com.furkanmulayim.benimsagligim.presentation.saved_future.SavedViewModel

class ForMeFragment : Fragment() {

    private lateinit var binding: FragmentForMeBinding
    private lateinit var vm: SavedViewModel
    private var adapter = ForMeAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_for_me, container, false)

        //viewmodel kullanacağımızı ve sınıfın hangisi olduğunu söylüyoruz
        vm = ViewModelProvider(this)[SavedViewModel::class.java]

        //adapter ayarlıyoruz
        binding.savedDiseases.layoutManager = LinearLayoutManager(requireContext())
        binding.savedDiseases.adapter = adapter

        vm.showDiseases()
        clickListeners()
        observeLiveData()
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

    private fun observeLiveData() {
        vm.savedDiseaseList.observe(viewLifecycleOwner, Observer {list->
            list.let {
                adapter.updateList(list)
            }
        })
    }

}