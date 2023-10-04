package com.example.fitnessandroid.homescreen.user

import java.util.Date

data class User(
    val email: String,
    val pseudo: String,
    val firstName: String,
    val lastName: String,
    val sex: String,
    val birthday: Date,
    val size: Float,
    val sizeType: String,
    val weight: Float,
    val weightType: String,
    val liquidType: String,
    val createdAt: Date
)
