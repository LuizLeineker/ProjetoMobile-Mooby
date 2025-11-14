package com.example.mooby.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mooby.data.entity.Meta
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MetaInputDialog(
    onDismiss: () -> Unit,
    onSave: (Meta) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var targetValueStr by remember { mutableStateOf("") }
    var deadlineStr by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Nova Meta", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") }
                )

                OutlinedTextField(
                    value = targetValueStr,
                    onValueChange = { targetValueStr = it },
                    label = { Text("Valor alvo") }
                )

                OutlinedTextField(
                    value = deadlineStr,
                    onValueChange = { deadlineStr = it },
                    label = { Text("Prazo (timestamp)") }
                )

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismiss) { Text("Cancelar") }
                    Button(onClick = {
                        val targetValue = targetValueStr.replace(",", ".").toDoubleOrNull() ?: 0.0
                        val deadline = deadlineStr.toLongOrNull() ?: System.currentTimeMillis()

                        onSave(
                            Meta(
                                userId = FirebaseAuth.getInstance().currentUser?.uid.orEmpty(),
                                title = title,
                                targetValue = targetValue,
                                progress = 0.0,
                                deadline = deadline
                            )
                        )
                        onDismiss()
                    }) { Text("Salvar") }
                }
            }
        }
    }
}