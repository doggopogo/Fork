package com.example.fitnessandroid.homescreen.toilet.user

import java.util.*

data class UserToilet(
    val idUserToilet: String,
    var timestamp: Date,
    var fecesType: Number,
    var numberOfUrine: Number,
)
