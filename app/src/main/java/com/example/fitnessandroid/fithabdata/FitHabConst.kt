package com.example.fitnessandroid.fithabdata

data class FitHabConst(
    var ActivityIntensityEnum : List<String> = listOf("light", "medium", "intense", "very intense"),
    var AlcoholFormatEnum : List<String> = listOf( "glass", "can", "bottle", "shooter"),
    var BloodSugarEnum : List<String> = listOf("mmol/L", "mg/dl"),
    var FoodEnum : List<String> = listOf("ml", "g", "kg", "lbs"),
    var HeightEnum : List<String> = listOf("m", "ft"),
    var LiquidEnum : List<String> = listOf("ml", "oz"),
    var SexEnum : List<String> = listOf("Male", "Female", "Non binary"),
    var SleepMindsetEnum : List<String> = listOf("rested", "happy", "tired", "angry"),
    var WeightEnum : List<String> = listOf("kg", "lbs")
)
