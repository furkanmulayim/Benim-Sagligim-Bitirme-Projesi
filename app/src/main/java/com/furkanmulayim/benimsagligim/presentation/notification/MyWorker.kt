package com.furkanmulayim.benimsagligim.presentation.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.furkanmulayim.benimsagligim.util.NotificationHelper
import com.furkanmulayim.benimsagligim.util.showPeriodicNotification

class MyWorker(
    var cont: Context,
    workerParams: WorkerParameters,
) : Worker(cont, workerParams) {
    override fun doWork(): Result {
        val baslik = inputData.getString("myString") ?: "BENİM SAĞLIĞIM"
        val aciklama = inputData.getString("myString2") ?: "İlaç Zamanı Geldi"

        NotificationHelper.createNotificationChannel(cont)
        showPeriodicNotification(cont, baslik,aciklama)

        return Result.success()
    }
}