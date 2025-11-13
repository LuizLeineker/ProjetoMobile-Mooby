package com.example.mooby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mooby.ui.screens.InitialScreen
import com.example.mooby.ui.theme.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "initial") {

        composable("initial") { InitialScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("transaction") { TransactionsScreen(navController) }
        composable("insights") { InsightsScreen(navController) }
        composable("tips") { TipsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("objective") { ObjectiveScreen(navController) }
    }
}
