package com.furkanmulayim.benimsagligim.presentation

import NotificationHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentNotificationBinding
import com.furkanmulayim.benimsagligim.databinding.FragmentSearchBinding
import com.furkanmulayim.benimsagligim.presentation.notification_future.NotificationViewModel
import com.furkanmulayim.benimsagligim.presentation.search_future.SearchViewModel
import java.util.Timer
import java.util.TimerTask


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }

    private fun deneme() {
        val notificationHelper = NotificationHelper(requireContext(), "seker ilacı", "yemekten önce iç", 34)

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                notificationHelper.showHourlyNotification()
            }
        }, 0, 5000) // Her saatte bir (3600000 milisaniye) çalışır.
    }

}