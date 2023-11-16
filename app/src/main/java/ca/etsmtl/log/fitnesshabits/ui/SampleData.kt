package ca.etsmtl.log.fitnesshabits.ui

import java.util.Date

// ----- Samples data for Preview ----- //

// *TO CHANGE: quand les models seront crees
data class Drink(
    val name: String,
    val format: String,
    val quantity: Int,
    val calories: Int,
    val carbohydrates: Float,
    val protein: Float,
    val fiber: Float,
    val fat: Float,
)

data class HydrationEntry(
    val drink: Drink,
    val timestamp: Date
)


object SampleData {
    val drinks = listOf(
        Drink("Eau Perrier", "Bouteille", 500, 0, 0.0f, 0.0f, 0.0f, 0.0f),
        Drink("Eau minéralisé", "Verre", 250, 0, 0.0f, 0.0f, 0.0f, 0.0f),
        Drink("Gatorade", "Canette", 330, 150, 35.0f, 0.0f, 0.0f, 0.0f),
        Drink("Bubbly", "Canette", 355, 0, 0.0f, 0.0f, 0.0f, 0.0f),
        Drink("Jus d'orange", "Verre", 250, 120, 29.0f, 1.0f, 0.5f, 0.2f),
        Drink(name = "Thé Vert", format = "Verre", quantity = 250, calories = 2, carbohydrates = 0.5f, protein = 0.1f, fiber = 0.0f, fat = 0.0f),
        Drink(name = "Jus de Pomme", format = "Verre", quantity = 200, calories = 95, carbohydrates = 24.0f, fiber = 0.5f, protein = 0.5f, fat = 0.1f),
        Drink(name = "Eau de Coco", format = "Canette", quantity = 330, calories = 46, carbohydrates = 9.0f, protein = 1.0f, fiber = 0.5f, fat = 0.2f)
    )
    val hydrationEntries = listOf(
        HydrationEntry(drinks[1], timestamp = Date(System.currentTimeMillis() - 259200000)), // Eau minéralisé (3 days ago)
        HydrationEntry(drinks[5], timestamp = Date(System.currentTimeMillis() - 777600000)), // Thé Vert (9 days ago)
        HydrationEntry(drinks[2], timestamp = Date(System.currentTimeMillis() - 86400000)), // Gatorade (1 day ago)
        HydrationEntry(drinks[0], timestamp = Date(System.currentTimeMillis() - 691200000)), // Eau Perrier (8 days ago)
        HydrationEntry(drinks[4], timestamp = Date(System.currentTimeMillis() - 172800000)), // Jus d'orange (2 days ago)
        HydrationEntry(drinks[3], timestamp = Date(System.currentTimeMillis() - 345600000)), // Bubbly (4 days ago)
        HydrationEntry(drinks[6], timestamp = Date(System.currentTimeMillis() - 432000000)), // Jus de Pomme (5 days ago)
        HydrationEntry(drinks[0], timestamp = Date()), // Eau Perrier (Current date)
    )

}