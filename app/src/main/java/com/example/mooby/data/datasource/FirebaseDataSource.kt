package com.example.mooby.data.datasource

import com.example.mooby.data.entity.TransactionEY
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun saveTransaction(transaction: TransactionEY) {
        db.collection("transactions")
            .document(transaction.id)
            .set(transaction)
            .await()
    }

    fun getTransactionsFlow(userId: String) = callbackFlow {
        val listener: ListenerRegistration = db.collection("transactions")
            .whereEqualTo("userId", userId)
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                } else {
                    val list = snapshot?.toObjects(TransactionEY::class.java) ?: emptyList()
                    trySend(list)
                }
            }
        awaitClose { listener.remove() }
    }
}
