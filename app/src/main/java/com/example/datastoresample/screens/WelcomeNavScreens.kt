package com.example.datastoresample.screens

import com.example.datastoresample.R

sealed class WelcomeNavScreens(
    val image: Int,
    val title: String,
    val description: String
) {
    object First : WelcomeNavScreens(
        R.drawable.image_1,
        "Ready to Travel",
        "Choose your destination, plan your trip. Pick the best place for your holiday."
    )

    object Second : WelcomeNavScreens(
        R.drawable.image_2,
        "Select the Date",
        "Select the day, pick your ticket. We give you the best price. We guarantee."
    )

    object Third : WelcomeNavScreens(
        R.drawable.image_3,
        "Feels Like Home",
        "Enjoy your holidays! Don't forget to take a drink and take a photo."
    )
}
