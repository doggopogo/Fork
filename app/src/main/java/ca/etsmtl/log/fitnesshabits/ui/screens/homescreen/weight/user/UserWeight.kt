package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.weight.user

import java.util.*

data class UserWeight(
    val idUserWeight: String,
    val weight: Number,
    val preferenceUnit: String,
    var timestamp: Date
)
