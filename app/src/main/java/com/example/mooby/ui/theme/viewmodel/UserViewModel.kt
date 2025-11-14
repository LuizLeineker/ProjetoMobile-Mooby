package com.example.mooby.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooby.data.entity.User
import com.example.mooby.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: User?) : UserState()
    data class Error(val message: String) : UserState()
}

class UserViewModel(private val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow<UserState>(UserState.Idle)
    val state: StateFlow<UserState> = _state.asStateFlow()

    init { fetchUser() }

    fun fetchUser() {
        viewModelScope.launch {
            try {
                _state.value = UserState.Loading
                val user = repository.getUser()
                _state.value = UserState.Success(user)
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Erro ao carregar usuário")
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            try {
                _state.value = UserState.Loading
                repository.saveUser(user)
                _state.value = UserState.Success(user)
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Erro ao salvar usuário")
            }
        }
    }
}
