package com.furkanmulayim.benimsagligim.presentation.search_future

import android.os.Bundle
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
import com.furkanmulayim.benimsagligim.util.SharedPrefs

class SearchFragment : Fragment() {
    //ui nesnelerim
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private var recogText: String = ""

    private var sp = SharedPrefs()

    //adapter nesnesi
    private var adapterSearchDisease = SearchAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        //ui bilessenleri
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        //bundle ile gelen verilerimiz
        arguments?.let {
            recogText = SearchFragmentArgs.fromBundle(it).recognition.toString()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.rcycSearch.let {
            //adapter ayarlamak için
            it.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.adapter = adapterSearchDisease
        }

        //gerekli fonksiyonları çağırıyoruz
        viewModel.refresh()
        observeTumHastalik()
        clickListeners()
    }

    private fun observeTumHastalik() {
        //tüm hastalıkları gözlemlemek için kullanıyoruz
        viewModel.allDiseaseList.observe(viewLifecycleOwner, Observer { tumHasta ->
            tumHasta.let {
                adapterSearchDisease.updateList(tumHasta)
                //burası önemli ms sürelerde çalıştığı için tüm hastalıklar gelince çalışıcak..
                searchBarControl()
            }
        })
    }

    private fun searchBarControl() {
        //arama cubuğunda yazılan metin için
        viewModel.searchControl(binding.sorguEditText, observeArananHastalik())

        //recognize edilen metni arama çubuğuna yazarak hastalık listeleme fonk. atıyoruz
        binding.sorguEditText.setText(recogText)
        viewModel.arananHastalikListele(recogText)
    }

    private fun observeArananHastalik() {
        //sadece aranan hastalıkalrı gözlemlemek için kullanıyoruz
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