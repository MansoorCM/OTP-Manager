package com.example.otpmanager.ui.navigation

sealed class Screen(val route: String) {
    data object Contacts : Screen("contacts")
    data object ContactSave : Screen("contact_save")
}