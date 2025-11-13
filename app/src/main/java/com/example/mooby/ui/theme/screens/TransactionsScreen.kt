package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TransactionsScreen(navController: NavController) {
    Scaffold(
        topBar = { Text("Transações", fontSize = 22.sp, modifier = Modifier.padding(16.dp)) },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* nova transação */ }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Histórico de transações", fontSize = 18.sp)
            Spacer(Modifier.height(8.dp))
            repeat(3) {
                Text("💵 Compra ${it + 1}: R$ ${(it + 1) * 50}", fontSize = 16.sp)
                Divider()
            }
        }
    }
}
