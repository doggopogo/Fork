package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol.user

import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol.Alcohol
import java.util.Date

data class UserAlcohol(
    val idUserAlcohol: String,
    var alcohol: Alcohol,
    var numberOfConsumption: Number,
    var timestamp: Date
)
