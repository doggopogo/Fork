package ca.etsmtl.log.fitnesshabits

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import ca.etsmtl.log.fitnesshabits.helper.NotificationHelper

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        instance = this
        notificationHelper = NotificationHelper(this, NotificationManagerCompat.from(this))
        notificationHelper.createNotificationChannel()
    }
}