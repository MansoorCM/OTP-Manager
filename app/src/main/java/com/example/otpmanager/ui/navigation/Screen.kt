package com.example.otpmanager.ui.navigation

sealed class Screen(val route: String) {
    data object Contacts : Screen("contacts")
    data object ContactSave : Screen("contact_save")
    data object ContactDetail : Screen("contact_detail/{contactId}") {
        fun createRoute(contactId: Int) = "contact_detail/$contactId"
    }

    data object ContactEdit : Screen("contact_edit/{contactId}") {
        fun createRoute(contactId: Int) = "contact_edit/$contactId"
    }
}