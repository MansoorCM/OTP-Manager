package com.example.otpmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.otpmanager.ui.ContactDetailScreen
import com.example.otpmanager.ui.ContactSaveScreen
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
                onSaveClick = { navController.navigate(Screen.ContactSave.route) },
                onDetailClick = { navController.navigate(Screen.ContactDetail.createRoute(it)) },
                viewModel = viewModel
            )
        }
        composable(Screen.ContactSave.route) {
            ContactSaveScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { viewModel.onSaveClicked(it) },
                viewModel = viewModel
            )
        }
        composable(Screen.ContactDetail.route,
            arguments = listOf(
                navArgument("contactId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("contactId") ?: 0
            ContactDetailScreen(
                { navController.popBackStack() },
                { navController.navigate(Screen.ContactEdit.createRoute(it)) },
                id,
                viewModel = viewModel
            )
        }
        composable(Screen.ContactEdit.route,
            arguments = listOf(
                navArgument("contactId") { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt("contactId") ?: 0
            ContactSaveScreen(
                id,
                { navController.popBackStack() },
                { viewModel.onUpdateClicked(it) },
                viewModel = viewModel
            )
        }
    }
}