package com.example.fitnessandroid.homescreen.bloodSugar.user

import java.util.*

data class UserBloodSugar(
    val idUserBloodSugar: String,
    val preferenceUnit: String,
    val bloodSugar: Double,
    val timestamp: Date,
)
