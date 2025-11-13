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
fun ObjectiveScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Metas Financeiras", fontSize = 22.sp) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Meta: Comprar notebook - 40% concluído", fontSize = 18.sp)
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(progress = 0.4f, Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            Button(onClick = { /* adicionar meta */ }) {
                Text("Nova meta")
            }
        }
    }
}
