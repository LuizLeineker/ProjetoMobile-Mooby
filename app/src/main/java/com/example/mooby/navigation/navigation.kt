package com.example.mooby.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mooby.data.repository.Repository
import com.example.mooby.ui.theme.screens.*
import com.example.mooby.ui.theme.viewmodel.MetaViewModel
import com.example.mooby.ui.theme.viewmodel.MyViewModelFactory
import com.example.mooby.ui.theme.viewmodel.TransactionState
import com.example.mooby.ui.theme.viewmodel.TransactionViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    // Repository
    val repository = Repository()

    // ViewModels
    val transactionVM: TransactionViewModel = viewModel(factory = MyViewModelFactory(repository))
    val metaVM: MetaViewModel = viewModel(factory = MyViewModelFactory(repository))

    // Lista de transações
    val state by transactionVM.state.collectAsState()
    val txList = if (state is TransactionState.Success) {
        (state as TransactionState.Success).transactions
    } else emptyList()

    NavHost(navController = navController, startDestination = "initial", modifier = modifier) {
        composable("initial") { InitialScreen(navController) }
        composable("main") { MainScreen(navController) }

        composable("transaction") {
            TransactionsScreen(
                navController = navController,
                transactions = txList,
                userId = auth.currentUser?.uid.orEmpty(),
                onAddTransaction = { transactionVM.insert(it) }
            )
        }

        composable("insights") {
            InsightsScreen(
                navController = navController,
                transactionViewModel = transactionVM
            )
        }

        composable("tips") { TipsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("objective") {
            ObjectiveScreen(
                navController = navController,
                metaViewModel = metaVM
            )
        }
    }
}
