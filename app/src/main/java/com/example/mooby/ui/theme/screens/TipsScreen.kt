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
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dicas Financeiras", fontSize = 22.sp) }
            )
        }
    ) { paddingValues ->
        // 👇 o conteúdo do Scaffold precisa receber o paddingValues aqui
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("💡 Dica do dia:")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Evite gastar por impulso. Espere 24h antes de comprar algo não essencial.")
        }
    }
}
