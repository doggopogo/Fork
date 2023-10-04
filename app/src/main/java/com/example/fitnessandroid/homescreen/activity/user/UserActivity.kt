package com.example.fitnessandroid.homescreen.activity.user

import com.example.fitnessandroid.homescreen.activity.Activity
import java.util.Date

data class UserActivity(
    val idUserActivity: String,
    var activity: Activity,
    var activityIntensity: String,
    var activityDuration: Number,
    var timestamp: Date
)
