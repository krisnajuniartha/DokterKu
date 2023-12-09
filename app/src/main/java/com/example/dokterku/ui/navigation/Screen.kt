package com.example.dokterku.ui.navigation

sealed class Screen(val route: String) {
    object Home:Screen("home")
    object Profile: Screen("profile")
    object DetailDokter: Screen("home/{userId}") {
        fun createRoute(userId: String) = "home/$userId"
    }
}