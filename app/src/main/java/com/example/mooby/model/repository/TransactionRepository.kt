package com.example.mooby.model.repository

import com.example.mooby.model.dao.TransactionDAO
import com.example.mooby.model.dto.TransactionDTO
import com.example.mooby.model.entity.TransactionEY
import com.example.mooby.model.mapper.toDTO
import com.example.mooby.model.mapper.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TransactionRepository(
    private val dao: TransactionDAO,
    private val firestore: FirebaseFirestore
) {

    private val collection = firestore.collection("transactions")

    // CRUD
    suspend fun insertLocal(transactionEY: TransactionEY) = dao.insertTransaction(transactionEY)
    suspend fun updateLocal(transactionEY: TransactionEY) = dao.updateTransaction(transactionEY)
    suspend fun deleteLocal(transactionEY: TransactionEY) = dao.deleteTransaction(transactionEY)
    fun getTransactionsByUser(userId: Int) = dao.getTransactionsByUserId(userId)

    // FIREBASE
    suspend fun syncToFirebase(transactionEY: TransactionEY) {
        val dto = transactionEY.toDTO()
        collection.document(dto.id).set(dto).await()
    }

    suspend fun fetchFromFirebase(userId: Int): List<TransactionEY> {
        val snapshot = collection
            .whereEqualTo("userId", userId.toString())
            .get()
            .await()

        return snapshot.documents.mapNotNull { it.toObject(TransactionDTO::class.java)?.toEntity() }
    }
}