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
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil do Usuário", fontSize = 22.sp) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Nome: Marcus Vinicius")
            Text("Email: marcus@example.com")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { navController.navigate("initial") }) {
                Text("Sair")
            }
        }
    }
}
