package com.example.mooby.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooby.model.entity.User
import com.example.mooby.model.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    object Success : UserState()
    data class Error(val message: String) : UserState()
}

class UserVM(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UserState>(UserState.Idle)
    val state: StateFlow<UserState> = _state

    suspend fun getUser(id: Int) = repository.getUserById(id)

    fun insert(user: User) {
        viewModelScope.launch {
            try {
                _state.value = UserState.Loading
                repository.insertLocal(user)
                repository.syncToFirebase(user)
                _state.value = UserState.Success
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Erro ao inserir usuário")
            }
        }
    }

    fun update(user: User) {
        viewModelScope.launch {
            try {
                _state.value = UserState.Loading
                repository.updateLocal(user)
                _state.value = UserState.Success
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Erro ao atualizar usuário")
            }
        }
    }
}
