import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.presentation.activity.MainActivity

class NotificationHelper(
    private val context: Context,
    private val name: String,
    private val detail: String,
    private val img: Int
) {

    private val channelId = "HourlyNotificationChannel"

    fun showHourlyNotification() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId, "Hourly Notifications", NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder =
            NotificationCompat.Builder(context, channelId).setSmallIcon(R.drawable.acil_arama_svg)
                .setContentTitle("Hourly Reminder")
                .setContentText("It's time for your hourly task.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
                .setAutoCancel(true)

        notificationManager.notify(0, builder.build())
    }
}
