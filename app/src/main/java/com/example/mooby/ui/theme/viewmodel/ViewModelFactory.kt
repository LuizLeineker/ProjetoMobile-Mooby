package com.example.mooby.ui.theme.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mooby.data.repository.Repository
import com.google.firebase.auth.FirebaseAuth

class MyViewModelFactory(
    private val repository: Repository,
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(auth) as T

            modelClass.isAssignableFrom(UserViewModel::class.java) ->
                UserViewModel(repository) as T

            modelClass.isAssignableFrom(MetaViewModel::class.java) ->
                MetaViewModel(repository) as T

            modelClass.isAssignableFrom(TransactionViewModel::class.java) ->
                TransactionViewModel(repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
