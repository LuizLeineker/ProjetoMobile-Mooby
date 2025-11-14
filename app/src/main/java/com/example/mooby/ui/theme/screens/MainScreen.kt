package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mooby.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Resumo Financeiro", fontSize = 24.sp) }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Saldo Atual", fontSize = 20.sp)
                    Text("R$ 1.250,00", fontSize = 28.sp, color = MaterialTheme.colorScheme.primary)
                }
            }

            Button(onClick = { navController.navigate(Routes.TRANSACTIONS) }, modifier = Modifier.fillMaxWidth()) { Text("Transações") }
            Button(onClick = { navController.navigate(Routes.OBJECTIVE) }, modifier = Modifier.fillMaxWidth()) { Text("Metas") }
            Button(onClick = { navController.navigate(Routes.TIPS) }, modifier = Modifier.fillMaxWidth()) { Text("Dicas Financeiras") }
            Button(onClick = { navController.navigate(Routes.INSIGHTS) }, modifier = Modifier.fillMaxWidth()) { Text("Gráficos") }
            Button(onClick = { navController.navigate(Routes.PROFILE) }, modifier = Modifier.fillMaxWidth()) { Text("Perfil") }
        }
    }
}
