package com.example.mooby.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mooby.data.entity.TransactionEY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionInputDialog(
    onDismiss: () -> Unit,
    onSave: (TransactionEY) -> Unit,
    userId: String
) {
    var type by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var valueStr by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text("Nova transação", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Tipo (despesa / receita)") }
                )

                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Categoria") }
                )

                OutlinedTextField(
                    value = valueStr,
                    onValueChange = { valueStr = it },
                    label = { Text("Valor") }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição (opcional)") }
                )

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) { Text("Cancelar") }

                    Button(onClick = {
                        val amount = valueStr.replace(",", ".").toDoubleOrNull() ?: 0.0

                        onSave(
                            TransactionEY(
                                id = "", // id vazio, será gerado no repository
                                userId = userId,
                                type = type,
                                category = category,
                                amount = amount,
                                timestamp = System.currentTimeMillis(),
                                description = description
                            )
                        )

                        onDismiss()
                    }) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}
