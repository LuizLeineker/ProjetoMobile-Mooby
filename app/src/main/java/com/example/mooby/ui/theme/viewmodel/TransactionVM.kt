package com.example.mooby.ui.theme.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooby.data.entity.TransactionEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TransactionState {
    object Idle : TransactionState()
    object Loading : TransactionState()
    object Success : TransactionState()
    data class Error(val message: String) : TransactionState()
}

class TransactionVM(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _state = MutableStateFlow<TransactionState>(TransactionState.Idle)
    val state: StateFlow<TransactionState> = _state

    fun getTransactions(userId: Int) =
        repository.getTransactionsByUser(userId)

    fun insert(transaction: TransactionEY) {
        viewModelScope.launch {
            try {
                _state.value = TransactionState.Loading
                repository.insertLocal(transaction)
                repository.syncToFirebase(transaction)
                _state.value = TransactionState.Success
            } catch (e: Exception) {
                _state.value = TransactionState.Error(e.message ?: "Erro ao inserir")
            }
        }
    }

    fun update(transaction: TransactionEY) {
        viewModelScope.launch {
            try {
                _state.value = TransactionState.Loading
                repository.updateLocal(transaction)
                repository.syncToFirebase(transaction)
                _state.value = TransactionState.Success
            } catch (e: Exception) {
                _state.value = TransactionState.Error(e.message ?: "Erro ao atualizar")
            }
        }
    }

    fun delete(transaction: TransactionEY) {
        viewModelScope.launch {
            try {
                _state.value = TransactionState.Loading
                repository.deleteLocal(transaction)
                _state.value = TransactionState.Success
            } catch (e: Exception) {
                _state.value = TransactionState.Error(e.message ?: "Erro ao excluir")
            }
        }
    }
}
