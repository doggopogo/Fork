package ca.etsmtl.log.fitnesshabits

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import ca.etsmtl.log.fitnesshabits.helpers.NotificationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        notificationHelper = NotificationHelper(this, NotificationManagerCompat.from(this))
        notificationHelper.createNotificationChannel()
    }
}