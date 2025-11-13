package com.example.mooby.ui.theme.viewmodel  // 👈 corrige o package para seguir o padrão do resto do app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("AuthViewModel", "Login bem-sucedido: ${auth.currentUser?.email}")
                            _authState.value = AuthState.Success
                        } else {
                            val errorMsg = task.exception?.message ?: "Erro desconhecido"
                            Log.e("AuthViewModel", "Falha no login: $errorMsg", task.exception)
                            _authState.value = AuthState.Error(errorMsg)
                        }
                    }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Exceção no login", e)
                _authState.value = AuthState.Error("Falha ao realizar login: ${e.localizedMessage}")
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("AuthViewModel", "Usuário cadastrado: ${auth.currentUser?.email}")
                            _authState.value = AuthState.Success
                        } else {
                            val errorMsg = task.exception?.message ?: "Erro desconhecido"
                            Log.e("AuthViewModel", "Falha no cadastro: $errorMsg", task.exception)
                            _authState.value = AuthState.Error(errorMsg)
                        }
                    }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Exceção no cadastro", e)
                _authState.value = AuthState.Error("Falha ao cadastrar: ${e.localizedMessage}")
            }
        }
    }

    fun logout() {
        try {
            auth.signOut()
            _authState.value = AuthState.Idle
            Log.d("AuthViewModel", "Usuário desconectado")
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Erro ao sair", e)
            _authState.value = AuthState.Error("Erro ao sair: ${e.localizedMessage}")
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}
