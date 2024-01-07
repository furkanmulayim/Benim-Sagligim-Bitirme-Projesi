package com.furkanmulayim.benimsagligim.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.presentation.activity.MainActivity

object NotificationHelper {

    const val channelId = "default_channel_id"

    fun createNotificationChannel(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }
}

fun showPeriodicNotification(context: Context, name: String, detail: String) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val builder =
        NotificationCompat.Builder(context, NotificationHelper.channelId).setSmallIcon(R.drawable.dot_rectangle)
            .setContentTitle(name).setContentText(detail)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)

    val uniqueID = System.currentTimeMillis().toInt()
    notificationManager.notify(uniqueID, builder.build())
}
