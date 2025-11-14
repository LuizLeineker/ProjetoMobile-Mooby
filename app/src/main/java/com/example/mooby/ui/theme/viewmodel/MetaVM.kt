package com.example.mooby.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooby.data.entity.Meta
import com.example.mooby.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class MetaState {
    object Idle : MetaState()
    object Loading : MetaState()
    data class Success(val metas: List<Meta> = emptyList()) : MetaState()
    data class Error(val message: String) : MetaState()
}

class MetaViewModel(private val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow<MetaState>(MetaState.Idle)
    val state: StateFlow<MetaState> = _state.asStateFlow()

    init { fetchMetas() }

    fun fetchMetas() {
        viewModelScope.launch {
            try {
                _state.value = MetaState.Loading
                repository.getMetas().collect { metas ->
                    _state.value = MetaState.Success(metas)
                }
            } catch (e: Exception) {
                _state.value = MetaState.Error(e.message ?: "Erro ao carregar metas")
            }
        }
    }

    fun insert(meta: Meta) {
        viewModelScope.launch {
            try {
                _state.value = MetaState.Loading
                repository.saveMeta(meta)
                fetchMetas()
            } catch (e: Exception) {
                _state.value = MetaState.Error(e.message ?: "Erro ao salvar meta")
            }
        }
    }
}
