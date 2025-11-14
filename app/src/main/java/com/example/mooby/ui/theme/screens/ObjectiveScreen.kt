package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mooby.data.entity.Meta
import com.example.mooby.ui.theme.viewmodel.MetaViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectiveScreen(
    navController: NavController,
    metaViewModel: MetaViewModel
) {
    val metasState by metaViewModel.state.collectAsState()
    val metasList = if (metasState is com.example.mooby.ui.theme.viewmodel.MetaState.Success) {
        (metasState as com.example.mooby.ui.theme.viewmodel.MetaState.Success).metas
    } else emptyList()

    var showDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newTarget by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Metas Financeiras", fontSize = 24.sp) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (metasList.isEmpty()) {
                Text("Nenhuma meta ainda", fontSize = 18.sp)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(metasList) { meta ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(meta.title, fontSize = 20.sp)
                            LinearProgressIndicator(
                                progress = (meta.progress / meta.targetValue).toFloat(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp),
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "${meta.progress} / ${meta.targetValue}",
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }

    // Dialog para nova meta
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Nova Meta") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Título") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newTarget,
                        onValueChange = { newTarget = it },
                        label = { Text("Valor alvo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val targetValue = newTarget.toDoubleOrNull() ?: 0.0
                    if (newTitle.isNotBlank() && targetValue > 0) {
                        val meta = Meta(
                            userId = "", // Firestore Repository vai pegar o UID
                            title = newTitle,
                            targetValue = targetValue,
                            progress = 0.0,
                            deadline = Calendar.getInstance().timeInMillis
                        )
                        metaViewModel.insert(meta)
                        newTitle = ""
                        newTarget = ""
                        showDialog = false
                    }
                }) { Text("Salvar") }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) { Text("Cancelar") }
            }
        )
    }
}
