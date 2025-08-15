package com.example.otpmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.otpmanager.ui.ContactDetailScreen
import com.example.otpmanager.ui.ContactScreen
import com.example.otpmanager.ui.ContactViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val viewModel: ContactViewModel = viewModel(factory = ContactViewModel.Factory)
    NavHost(
        navController,
        startDestination = Screen.Contacts.route,
        modifier = Modifier
    ) {
        composable(Screen.Contacts.route) {
            ContactScreen(
                onDetailClick = { navController.navigate(Screen.ContactDetail.route) },
                viewModel = viewModel
            )
        }
        composable(Screen.ContactDetail.route) {
            ContactDetailScreen(
                { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}