package com.example.mooby.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class TransactionState {
    object Idle : TransactionState()
    object Loading : TransactionState()
    data class Success(val transactions: List<TransactionEY> = emptyList()) : TransactionState()
    data class Error(val message: String) : TransactionState()
}

class TransactionViewModel(private val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow<TransactionState>(TransactionState.Idle)
    val state: StateFlow<TransactionState> = _state.asStateFlow()

    init {
        fetchTransactions()
    }

    fun fetchTransactions() {
        viewModelScope.launch {
            try {
                _state.value = TransactionState.Loading
                repository.getTransactions().collect { transactions ->
                    _state.value = TransactionState.Success(transactions)
                }
            } catch (e: Exception) {
                _state.value = TransactionState.Error(e.message ?: "Erro ao carregar transações")
            }
        }
    }

    fun insert(transaction: TransactionEY) {
        viewModelScope.launch {
            try {
                _state.value = TransactionState.Loading
                repository.saveTransaction(transaction)
                fetchTransactions() // atualiza lista após salvar
            } catch (e: Exception) {
                _state.value = TransactionState.Error(e.message ?: "Erro ao salvar transação")
            }
        }
    }
}
