package com.example.otpmanager.ui.navigation

sealed class Screen(val route: String) {
    data object Contacts : Screen("contacts")
    data object ContactDetail : Screen("contact_detail")
}