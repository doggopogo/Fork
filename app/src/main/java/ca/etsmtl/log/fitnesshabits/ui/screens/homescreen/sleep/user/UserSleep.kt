package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.sleep.user

import java.util.*

data class UserSleep(
    val idUserSleep: String,
    val startSleep: Date,
    val endSleep: Date,
    val numberOfAwakening: Number,
    val mindset: String,
    val totalSleepTime: Number,
    var timestamp: Date
)
