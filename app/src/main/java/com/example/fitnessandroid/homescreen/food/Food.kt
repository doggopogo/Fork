package com.example.fitnessandroid.homescreen.food

data class Food(
    val idFood: String,
    val foodName: String,
    val unit: String,
    val amount: Int,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val sugar: Float,
    val fiber: Float,
    val totalFat: Float,
    val saturatedFat: Float,
    val cholesterol: Float = 0f,
    val calcium: Float = 0f,
    val iron: Float = 0f,
    val sodium: Float = 0f,
    val potassium: Float = 0f,
    val magnesium: Float = 0f,
    val phosphorus: Float = 0f,
    val thiamine: Float = 0f,
    val riboflavin: Float = 0f,
)
