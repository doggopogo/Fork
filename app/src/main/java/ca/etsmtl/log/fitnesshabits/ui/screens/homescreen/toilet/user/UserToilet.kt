package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.toilet.user

import java.util.*

data class UserToilet(
    val idUserToilet: String,
    var timestamp: Date,
    var fecesType: Number,
    var numberOfUrine: Number,
)
