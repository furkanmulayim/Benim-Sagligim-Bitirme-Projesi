package com.furkanmulayim.benimsagligim.presentation.notification_future

import NotificationHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentNotificationBinding
import java.util.Timer
import java.util.TimerTask

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        back()
        start()
        //deneme()
    }

    private fun back() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_notificationFragment_to_forMeFragment)
        }
    }

    private fun start(){
        binding.notificationCreate.setOnClickListener {
            deneme(binding.notTitle.text.toString(), binding.notDescript.text.toString(), binding.notHours.text.toString().toInt())

        }
    }

    private fun deneme(a:String,b:String, c:Int) {
       val x = (c*1000).toLong()
        val notificationHelper =
            NotificationHelper(requireContext(), a, b)

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                notificationHelper.showHourlyNotification()
            }
        }, 0, x) // Her saatte bir (3600000 milisaniye) çalışır.
    }

}