package com.example.mooby.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooby.model.entity.Meta
import com.example.mooby.model.repository.MetaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MetaState {
    object Idle : MetaState()
    object Loading : MetaState()
    object Success : MetaState()
    data class Error(val message: String) : MetaState()
}

class MetaViewModel(
    private val repository: MetaRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MetaState>(MetaState.Idle)
    val state: StateFlow<MetaState> = _state

    fun getMetasByUser(userId: Int) =
        repository.getMetasByUser(userId)

    fun insert(meta: Meta) {
        viewModelScope.launch {
            try {
                _state.value = MetaState.Loading
                repository.insertLocal(meta)
                repository.syncToFirebase(meta)
                _state.value = MetaState.Success
            } catch (e: Exception) {
                _state.value = MetaState.Error(e.message ?: "Erro ao inserir meta")
            }
        }
    }

    fun update(meta: Meta) {
        viewModelScope.launch {
            try {
                _state.value = MetaState.Loading
                repository.updateLocal(meta)
                repository.syncToFirebase(meta)
                _state.value = MetaState.Success
            } catch (e: Exception) {
                _state.value = MetaState.Error(e.message ?: "Erro ao atualizar meta")
            }
        }
    }

    fun delete(meta: Meta) {
        viewModelScope.launch {
            try {
                _state.value = MetaState.Loading
                repository.deleteLocal(meta)
                _state.value = MetaState.Success
            } catch (e: Exception) {
                _state.value = MetaState.Error(e.message ?: "Erro ao deletar meta")
            }
        }
    }
}