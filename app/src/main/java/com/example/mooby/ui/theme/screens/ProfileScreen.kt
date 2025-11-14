package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Perfil", fontSize = 24.sp) }) }) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(20.dp)) {
                    Text("Marcus Vinicius", fontSize = 22.sp)
                    Text("marcus@example.com", fontSize = 16.sp)
                }
            }

            Button(onClick = { navController.navigate("initial") }, modifier = Modifier.fillMaxWidth()) { Text("Sair") }
        }
    }
}
