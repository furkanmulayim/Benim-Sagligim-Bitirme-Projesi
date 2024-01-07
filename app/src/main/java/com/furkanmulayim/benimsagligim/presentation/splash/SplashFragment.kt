package com.furkanmulayim.benimsagligim.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    //ui bilesenlerim
    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        //internet var mı yok mu fonksiyonu açılınca çağrıldı
        viewModel.checkInternetConnection()
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.isInternetAvailable.observe(viewLifecycleOwner, Observer { connect ->
            if (connect == true) {
                //eğer bağlantı varsa
                viewModel.baglantiVar(requireView())
            } else {
                //eğer bağlantı yoksa
                binding.baglaniYokButton.visibility = View.VISIBLE
                baglantiClickListener()
            }

        })
    }


    private fun baglantiClickListener() {
        binding.button.setOnClickListener {
            binding.baglaniYokButton.visibility = View.GONE
            viewModel.baglantiYok()
        }
    }

}