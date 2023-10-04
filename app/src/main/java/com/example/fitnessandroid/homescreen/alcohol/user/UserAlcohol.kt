package com.example.fitnessandroid.homescreen.alcohol.user

import com.example.fitnessandroid.homescreen.alcohol.Alcohol
import java.util.Date

data class UserAlcohol(
    val idUserAlcohol: String,
    var alcohol: Alcohol,
    var numberOfConsumption: Number,
    var timestamp: Date
)
