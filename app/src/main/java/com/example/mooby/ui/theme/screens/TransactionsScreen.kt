package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.ui.theme.components.TransactionInputDialog
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    navController: NavController,
    transactions: List<TransactionEY>,
    userId: String,
    onAddTransaction: (TransactionEY) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Transações", fontSize = 22.sp) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            if (transactions.isEmpty()) {
                Text("Nenhuma transação ainda. Toque no + para adicionar.")
            } else {
                val sdf = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
                transactions.forEach { tx ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(tx.category, fontSize = 18.sp)
                            Text(
                                "R$ ${"%.2f".format(tx.amount)}",
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            val formattedDate = try { sdf.format(Date(tx.timestamp)) } catch (_: Exception) { "Data inválida" }
                            Text(formattedDate)
                            if (tx.description.isNotEmpty()) Text(tx.description)
                        }
                    }
                }
            }
        }

        if (showDialog) {
            TransactionInputDialog(
                onDismiss = { showDialog = false },
                onSave = { onAddTransaction(it); showDialog = false },
                userId = userId
            )
        }
    }
}
