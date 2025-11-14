package com.example.mooby.ui.screens

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
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by authViewModel.authState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Criar Conta", fontSize = 20.sp) }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text("Email") }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = password, onValueChange = { password = it },
                label = { Text("Senha (6+)") }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    if (email.isNotBlank() && password.length >= 6) {
                        authViewModel.register(email, password)
                    } else Toast.makeText(context, "Preencha corretamente", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Criar") }

            Spacer(Modifier.height(12.dp))
            when (state) {
                is AuthState.Loading -> CircularProgressIndicator()
                is AuthState.Error -> Text("Erro: ${(state as AuthState.Error).message}", color = MaterialTheme.colorScheme.error)
                is AuthState.Success -> LaunchedEffect(Unit) {
                    Toast.makeText(context, "Conta criada", Toast.LENGTH_SHORT).show()
                    navController.navigate("initial") { popUpTo("register") { inclusive = true } }
                }
                else -> Unit
            }
        }
    }
}
