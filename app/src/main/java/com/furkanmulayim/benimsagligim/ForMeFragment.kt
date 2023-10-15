package com.furkanmulayim.benimsagligim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.furkanmulayim.benimsagligim.databinding.FragmentForMeBinding

class ForMeFragment : Fragment() {

    private lateinit var binding: FragmentForMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_for_me, container, false)

        return binding.root
    }


}