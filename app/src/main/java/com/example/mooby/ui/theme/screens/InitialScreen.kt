package com.example.mooby.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mooby.ui.theme.viewmodel.AuthViewModel
import com.example.mooby.ui.theme.viewmodel.AuthState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by authViewModel.authState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mooby — Login") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Bem-vindo!", fontSize = 26.sp)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() && password.length >= 6) {
                        authViewModel.login(email, password)
                    } else {
                        Toast.makeText(
                            context,
                            "Informe um email válido e senha com pelo menos 6 caracteres",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = state !is AuthState.Loading
            ) {
                Text("Entrar")
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    if (email.isNotBlank() && password.length >= 6) {
                        authViewModel.register(email, password)
                    } else {
                        Toast.makeText(
                            context,
                            "Informe um email válido e senha com pelo menos 6 caracteres",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = state !is AuthState.Loading
            ) {
                Text("Cadastrar-se")
            }

            Spacer(Modifier.height(24.dp))

            // Mensagens de status
            when (state) {
                is AuthState.Loading -> {
                    CircularProgressIndicator(Modifier.size(40.dp))
                }

                is AuthState.Error -> {
                    val message = (state as AuthState.Error).message
                    Text(
                        text = "❌ Erro: $message",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 14.sp
                    )
                }

                is AuthState.Success -> {
                    Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    // Redireciona para tela principal
                    LaunchedEffect(Unit) {
                        navController.navigate("main") {
                            popUpTo("initial") { inclusive = true }
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}
