package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Dicas Financeiras", fontSize = 24.sp) }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("💡 Dica do dia", fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Text("Evite compras por impulso. Aguarde 24h antes de comprar algo não essencial.", fontSize = 16.sp)
        }
    }
}
