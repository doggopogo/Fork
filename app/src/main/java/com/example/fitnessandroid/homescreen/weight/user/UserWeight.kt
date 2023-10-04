package com.example.fitnessandroid.homescreen.weight.user

import java.util.*

data class UserWeight(
    val idUserWeight: String,
    val weight: Number,
    val preferenceUnit: String,
    var timestamp: Date
)
