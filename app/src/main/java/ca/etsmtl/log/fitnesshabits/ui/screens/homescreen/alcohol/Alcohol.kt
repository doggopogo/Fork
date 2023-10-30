package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol

data class Alcohol(
    val idAlcohol: String,
    val alcoholName: String,
    val amount: Int,
    val unit: String,
    val format: String,
    val percentAlcohol: Float,
    val calories: Int,
    val protein: Float,
    val carbs: Float,
    val fiber: Float,
    val fat: Float
)
