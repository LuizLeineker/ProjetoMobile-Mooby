package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mooby.ui.theme.components.LineChartCompose
import com.example.mooby.ui.theme.viewmodel.TransactionState
import com.example.mooby.ui.theme.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    val state by transactionViewModel.state.collectAsState()

    // Pega as transações apenas se estiver no estado Success
    val transactions = (state as? TransactionState.Success)?.transactions ?: emptyList()

    val months = remember { mutableStateListOf<String>() }
    val totals = remember { mutableStateListOf<Float>() }

    val sdf = SimpleDateFormat("MMM", Locale.getDefault())

    LaunchedEffect(transactions) {
        months.clear()
        totals.clear()

        // Inicializa últimos 6 meses
        for (i in 5 downTo 0) {
            val cal = Calendar.getInstance()
            cal.add(Calendar.MONTH, -i)
            months.add(sdf.format(cal.time))
            totals.add(0f)
        }

        // Soma os valores por mês
        transactions.forEach { tx ->
            val month = sdf.format(Date(tx.timestamp))
            val index = months.indexOf(month)
            if (index >= 0) totals[index] += tx.amount.toFloat()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Insights Financeiros", fontSize = 22.sp) }) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            when (state) {
                is TransactionState.Loading -> CircularProgressIndicator()
                is TransactionState.Error -> Text((state as TransactionState.Error).message)
                is TransactionState.Success, TransactionState.Idle -> {
                    Text("Gastos Mensais", style = MaterialTheme.typography.headlineSmall)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text("Últimos 6 meses", fontSize = 18.sp)
                            Spacer(Modifier.height(16.dp))
                            LineChartCompose(
                                values = totals,
                                labels = months,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(260.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
