package ca.etsmtl.log.fitnesshabits

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import ca.etsmtl.log.fitnesshabits.di.data.database.AppDatabaseModule
import ca.etsmtl.log.fitnesshabits.helpers.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class App : Application() {
    lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        notificationHelper = NotificationHelper(this, NotificationManagerCompat.from(this))
        notificationHelper.createNotificationChannel()
        initializeDatabase()
    }

    private fun initializeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabaseModule.provideAppDatabase(applicationContext)
        }
    }
}