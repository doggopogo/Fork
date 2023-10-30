package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.activity.user

import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.activity.Activity
import java.util.Date

data class UserActivity(
    val idUserActivity: String,
    var activity: Activity,
    var activityIntensity: String,
    var activityDuration: Number,
    var timestamp: Date
)
