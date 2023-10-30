package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.user

import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.Food
import java.util.Date

data class UserFood(
    val idUserFood: String,
    var food: Food,
    var numberOfConsumption: Number,
    var timestamp: Date
)
