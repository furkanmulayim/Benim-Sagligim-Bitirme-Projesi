package com.furkanmulayim.benimsagligim.presentation.notification

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentNotificationBinding
import com.furkanmulayim.benimsagligim.util.showMessage
import java.util.concurrent.TimeUnit

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
        clickListeners()
    }

    private fun clickListeners() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_notificationFragment_to_forMeFragment)
        }

        binding.notificationCreate.setOnClickListener {
            notificationStart()
        }
    }

    private fun notificationStart() {
        //Kullanıcı Girdileri
        val textTitle = binding.notTitle.text.toString()
        val textDescr = binding.notDescript.text.toString()
        val textTime = binding.notHours.text.toString()

        //Kullanıcı Girdileri Kontorl
        val control = viewModel.control(textTitle, textDescr, textTime)
        if (control) {
            startSyncWorker(textTitle, textDescr, textTime)
        } else {
            requireActivity().showMessage("Lütfen Girdiğiniz Metinleri Kontrol Ediniz!")
        }
    }


    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun startSyncWorker(textTitle: String, textDescr: String, textHour: String) {
        val workManager = WorkManager.getInstance(requireContext())
        val myData =
            Data.Builder().putString("myString", textTitle).putString("myString2", textDescr)
                .build()
        val request =
            PeriodicWorkRequest.Builder(MyWorker::class.java, textHour.toLong(), TimeUnit.HOURS)
                .setInputData(myData).build()
        workManager.enqueue(request)
    }

}