package com.example.otpmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.otpmanager.ui.ContactDetailScreen
import com.example.otpmanager.ui.ContactScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = Screen.Contacts.route,
        modifier = Modifier
    ) {
        composable(Screen.Contacts.route) {
            ContactScreen(onDetailClick = { navController.navigate(Screen.ContactDetail.route) })
        }
        composable(Screen.ContactDetail.route) {
            ContactDetailScreen({ navController.popBackStack() })
        }
    }
}