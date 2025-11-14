package com.example.mooby.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
        topBar = { TopAppBar(title = { Text("Mooby", fontSize = 24.sp) }) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Acesse sua conta", fontSize = 22.sp)
                    Spacer(Modifier.height(20.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Senha") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = { authViewModel.login(email, password) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Entrar") }

                    Spacer(Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = { authViewModel.register(email, password) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Criar conta") }

                    Spacer(Modifier.height(16.dp))

                    when (state) {
                        is AuthState.Loading -> CircularProgressIndicator()
                        is AuthState.Error -> Text(
                            text = "Erro: ${(state as AuthState.Error).message}",
                            color = MaterialTheme.colorScheme.error
                        )
                        is AuthState.Success -> LaunchedEffect(Unit) {
                            Toast.makeText(context, "Bem-vindo!", Toast.LENGTH_SHORT).show()
                            navController.navigate("main") { popUpTo("initial") { inclusive = true } }
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}
