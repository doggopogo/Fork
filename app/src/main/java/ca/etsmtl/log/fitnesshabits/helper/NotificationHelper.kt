package ca.etsmtl.log.fitnesshabits.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ca.etsmtl.log.fitnesshabits.R

class NotificationHelper(
    private val context: Context,
    private val notificationManager: NotificationManagerCompat,
) {
    companion object {
        private const val CHANNEL_ID = "general_notification"
        private const val CHANNEL_NAME = "General notification"
        private const val CHANNEL_DESCRIPTION = "For your generaliest notifications"
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESCRIPTION
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showBasicNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.icon_logo) // Your app's notification icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1, builder.build())
    }

    fun showExpandableNotification(
        title: String,
        message: String,
        longMessage: String
    ) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.icon_logo) // Your app's notification icon
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(longMessage)
            )

            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(2, builder.build())
    }

    fun areNotificationsEnabled(): Boolean {
        return notificationManager.areNotificationsEnabled()
    }

    // source: https://developer.android.com/develop/ui/views/notifications/build-notification
}