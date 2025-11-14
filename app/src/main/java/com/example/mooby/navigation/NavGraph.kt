package com.example.mooby.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mooby.data.repository.Repository
import com.example.mooby.ui.screens.RegisterScreen
import com.example.mooby.ui.theme.screens.*
import com.example.mooby.ui.theme.viewmodel.MetaState
import com.example.mooby.ui.theme.viewmodel.MetaViewModel
import com.example.mooby.ui.theme.viewmodel.TransactionState
import com.example.mooby.ui.theme.viewmodel.TransactionViewModel
import com.example.mooby.ui.theme.viewmodel.MyViewModelFactory
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Repository: já decide se usa emulador local ou Firestore Cloud
    val repository = Repository()

    // ViewModels
    val transactionViewModel: TransactionViewModel = viewModel(
        factory = MyViewModelFactory(repository)
    )
    val metaViewModel: MetaViewModel = viewModel(
        factory = MyViewModelFactory(repository)
    )

    val auth = FirebaseAuth.getInstance()

    // Coleta de transações
    val transactionsState = transactionViewModel.state.collectAsState(initial = TransactionState.Idle).value
    val txList = if (transactionsState is TransactionState.Success) transactionsState.transactions else emptyList()

    // Coleta de metas
    val metasState = metaViewModel.state.collectAsState(initial = MetaState.Idle).value
    val metasList = if (metasState is MetaState.Success) metasState.metas else emptyList()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        modifier = modifier
    ) {
        composable(Routes.SPLASH) { SplashScreen(navController) }
        composable(Routes.INITIAL) { InitialScreen(navController) }
        composable(Routes.REGISTER) { RegisterScreen(navController) }
        composable(Routes.MAIN) { MainScreen(navController) }

        composable(Routes.TRANSACTIONS) {
            TransactionsScreen(
                navController = navController,
                transactions = txList,
                userId = auth.currentUser?.uid.orEmpty(),
                onAddTransaction = { transactionViewModel.insert(it) }
            )
        }

        composable(Routes.OBJECTIVE) {
            ObjectiveScreen(
                navController = navController,
                metaViewModel = metaViewModel
            )
        }

        composable(Routes.INSIGHTS) {
            InsightsScreen(
                navController = navController,
                transactionViewModel = transactionViewModel
            )
        }

        composable(Routes.PROFILE) { ProfileScreen(navController) }
        composable(Routes.TIPS) { TipsScreen(navController) }
    }
}
