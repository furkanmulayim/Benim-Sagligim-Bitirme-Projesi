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
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentDiseaseDetailBinding

class DiseaseDetailFragment : Fragment() {

    private lateinit var binding: FragmentDiseaseDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var diseaseUuid = 0
    private var adapterSimilar = SimilarDiseaseAdapter(arrayListOf())
    private var adapterHastags = DetailSymptomAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        //ui nesnelerine erisebilmek amacıyla binding mesnesi oluşturup layout veriyoruz
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_detail, container, false)

        //viewmodel kullanacağımızı ve sınıfın hangisi olduğunu söylüyoruz
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        //kategori list adaptöründen gelen uuidleri bundle aracılığı ile alıyoruz
        arguments?.let {
            diseaseUuid = DiseaseDetailFragmentArgs.fromBundle(it).uuid
        }
        //Detay Adaptörünü ayarlayarak esitliyoruz
        binding.rcycDetailsHastags.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcycDetailsHastags.adapter = adapterHastags

        //BenzerList Adaptörünü ayarlayarak esitliyoruz
        binding.benzerRcyc.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.benzerRcyc.adapter = adapterSimilar

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //görünümdeki viewmodel ile oluşturduğumuz viewmodeli bağladık
        binding.viewModel = viewModel

        //çeşitli fonksiyonlar..
        viewModel.refresh(diseaseUuid)
        onClickListeners()
        observeLiveData()

    }

    private fun onClickListeners() {
        //button click olaylarını dinliyoruz
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun semaEsitle(risk: Float, gorulme: Float) {
        //pieChart oluşturmak için viewModel'e verileri Gönderiyoruz
        viewModel.pieChartEsitle(binding.pieChartRisk, risk, binding.pieChartGorulme, gorulme)

    }

    fun gorselEsitle(link: String) {
        //linkini verdiğimiz görsellerimizi image viewa basar
        viewModel.gorselEsitle(binding.shapeableImageView, link)
    }

    private fun observeLiveData() {

        viewModel.let { vm ->
            vm.hastalik.observe(viewLifecycleOwner, Observer { hast ->
                hast.let {
                    //gelenleri ui uzerie basıyoruz
                    binding.let { b ->
                        b.hastalikAdiDetay.text = hast.adi
                        b.hastalikAdiLatinceDetay.text = hast.latinAd
                        b.hakkindaDetay.text = hast.hakkinda
                        b.hastalikKorunmaYollariDetay.text = hast.korunmaYollari
                        b.enfekteOldum.text = hast.enfekteOldum
                        b.riskText.text = hast.riskOrani
                        b.gorulmeText.text = hast.gorulmeSikligi
                    }

                    //gorulme ve risk oranlarını tip dönüşümü yaparak sema gösterme sınıfına gönderiyoruz
                    val gorulme = hast.gorulmeSikligi.toFloat()
                    val risk = hast.riskOrani.toFloat()
                    semaEsitle(risk, gorulme)

                    val link = vm.hastalik.value?.gorselLinki.toString()
                    gorselEsitle(link)

                    //etiket listesi ve benzer hastalıklar listelerini ayarlıyoruz
                    val etiketList = vm.etiketAyiklaEsitle(hast.etiketler) as ArrayList<String>
                    vm.getSimilar(hast.benzerHastaliklar)

                    // etiketleri adaptöre gönderiyoruz
                    adapterHastags.updateList(etiketList)

                    //benzer hastalıkları gözlemliyoruz
                    vm.similarDiseaseList.observe(viewLifecycleOwner, Observer {
                        if (it.isNotEmpty()) {
                            //benezer hastalıklar adaptörüne benzer listemizi veriyoruz
                            adapterSimilar.updateList(it)
                        }
                    })
                }
            })
        }
    }
}