package com.furkanmulayim.benimsagligim.presentation.search_future

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    //ui nesnelerim
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    //adapter nesnesi
    private var adapterSearchDisease = SearchAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        //adapter ayarlama
        binding.rcycSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcycSearch.adapter = adapterSearchDisease

        //gerekli fonksiyonları çağırıyoruz
        viewModel.refresh()
        clickListeners()
        searchControl()
    }

    private fun searchControl() {
        viewModel.searchControl(binding.sorguEditText, arananHastalik())
    }

    private fun arananHastalik() {
        viewModel.seciliHasta.observe(viewLifecycleOwner, Observer { hasta ->
            hasta.let {
                adapterSearchDisease.updateList(hasta)
            }
        })
    }

    private fun clickListeners() {
        //Geri butonu tıklanınca ana sayfaya dönecek.
        binding.backButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_searchFragment_to_homeFragment)
        }
        binding.scanCamera.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_searchFragment_to_scanSearchFragment)
        }
    }
}