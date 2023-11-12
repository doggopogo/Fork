package ca.etsmtl.log.fitnesshabits

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.repositories.HydrationRepository
import ca.etsmtl.log.fitnesshabits.helpers.NotificationHelper

class App : Application() {
    // The repository is initialized here and can be accessed throughout the app
    val hydrationRepository: HydrationRepository by lazy {
        val database = AppDatabase.getDatabase(this)
        HydrationRepository(database.hydrationDao())
    }

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