package com.furkanmulayim.benimsagligim.presentation.result

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import com.furkanmulayim.benimsagligim.presentation.detail.DetailSymptomAdapter

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
        mhrs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        //Adaptör Ayarlıyoruz
        binding.rcycSonuc.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcycSonuc.adapter = adapterResult
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
                //listemizin boş olma durumunda kullanıcı uyarılması
                listeBosIse()
            }

            //eski listeyi yemizliyoruz
            adapterResult.clearList()

            //verileri göstermeden önce duplike olan hastalıkları temizliyoruz
            viewModel.removeDuplicateDiseases()
            adapterResult.updateList(diseaseList as ArrayList<Disease>)
        })
    }

    fun listeBosIse() {
        binding.aciklama.text = getString(R.string.bir_sorun_var_sonuc)
    }

    private fun mhrs() {

        binding.mhrsPage.setOnClickListener {
            val url = "https://www.mhrs.gov.tr/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            requireActivity().startActivity(intent)

        }
    }


}