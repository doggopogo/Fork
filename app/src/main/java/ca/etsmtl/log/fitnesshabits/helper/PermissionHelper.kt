package ca.etsmtl.log.fitnesshabits.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {
    private const val notificationPermission = Manifest.permission.POST_NOTIFICATIONS
    fun hasNotificationPermission(context: Context): Boolean {
        return hasPermission(context, notificationPermission)
    }

    fun requestNotificationPermission(context: Context) {
        requestPermission(context, notificationPermission, 1)
    }

    private fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(context: Context, permission: String, requestCode: Int) {
        if (context is Activity) {
            ActivityCompat.requestPermissions(context, arrayOf(permission), requestCode)
        } else {
            throw IllegalArgumentException("Context used to request permission is not an Activity")
        }
    }
}
