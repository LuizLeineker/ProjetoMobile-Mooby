package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Text("Resumo Financeiro", fontSize = 22.sp, modifier = Modifier.padding(16.dp)) },
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Saldo atual: R$ 1.250,00", fontSize = 18.sp)
            Spacer(Modifier.height(12.dp))
            Button(onClick = { navController.navigate("transaction") }) { Text("Transações") }
            Button(onClick = { navController.navigate("objective") }) { Text("Metas") }
            Button(onClick = { navController.navigate("tips") }) { Text("Dicas") }
            Button(onClick = { navController.navigate("insights") }) { Text("Gráficos") }
            Button(onClick = { navController.navigate("profile") }) { Text("Perfil") }
        }
    }
}
