package com.example.fitnessandroid.homescreen.food.user

import com.example.fitnessandroid.homescreen.food.Food
import java.util.Date

data class UserFood(
    val idUserFood: String,
    var food: Food,
    var numberOfConsumption: Number,
    var timestamp: Date
)
