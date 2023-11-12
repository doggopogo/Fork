package ca.etsmtl.log.fitnesshabits.ui

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

object SampleData {
    val drinks = listOf(
        Drink(
            "Eau Perrier",
            "Bouteille",
            500,
            0,
            0.0f,
            0.0f,
            0.0f,
            0.0f
        ),
        Drink(
            "Eau minéralisé",
            "Verre",
            250,
            0,
            0.0f,
            0.0f,
            0.0f,
            0.0f
        ),
        Drink(
            "Gatorade",
            "Canette",
            330,
            150,
            35.0f,
            0.0f,
            0.0f,
            0.0f
        ),
        Drink(
            "Bubbly",
            "Canette",
            355,
            0,
            0.0f,
            0.0f,
            0.0f,
            0.0f
        ),
        Drink("Jus d'orange",
            "Verre",
            250,
            120,
            29.0f,
            1.0f,
            0.5f,
            0.2f
        ),
        Drink(
            name = "Thé Vert",
            format = "Verre",
            quantity = 250,
            calories = 2,
            carbohydrates = 0.5f,
            protein = 0.1f,
            fiber = 0.0f,
            fat = 0.0f
        ),
        Drink(
            name = "Jus de Pomme",
            format = "Verre",
            quantity = 200,
            calories = 95,
            carbohydrates = 24.0f,
            protein = 0.5f,
            fiber = 0.5f,
            fat = 0.1f
        ),
        Drink(
            name = "Eau de Coco",
            format = "Canette",
            quantity = 330,
            calories = 46,
            carbohydrates = 9.0f,
            protein = 1.0f,
            fiber = 0.5f,
            fat = 0.2f
        )
    )
}