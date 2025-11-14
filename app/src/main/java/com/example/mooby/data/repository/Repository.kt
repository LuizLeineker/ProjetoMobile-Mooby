package com.example.mooby.data.repository

import com.example.mooby.data.entity.Meta
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class Repository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun saveTransaction(t: TransactionEY) {
        val id = if (t.id.isEmpty()) db.collection("transactions").document().id else t.id
        val toSave = t.copy(id = id)
        db.collection("transactions").document(id).set(toSave).await()
    }

    fun getTransactions() = callbackFlow {
        val uid = auth.currentUser?.uid ?: ""
        val listener = db.collection("transactions")
            .whereEqualTo("userId", uid)
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val list = snapshot?.toObjects(TransactionEY::class.java) ?: emptyList()
                trySend(list)
            }
        awaitClose { listener.remove() }
    }

    suspend fun saveMeta(meta: Meta) {
        val id = meta.id.toString().ifEmpty { db.collection("metas").document().id }
        db.collection("metas").document(id).set(meta).await()
    }

    fun getMetas() = callbackFlow<List<Meta>> {
        val uid = auth.currentUser?.uid ?: ""
        val listener = db.collection("metas")
            .whereEqualTo("userId", uid)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val list = snapshot?.toObjects(Meta::class.java) ?: emptyList()
                trySend(list)
            }
        awaitClose { listener.remove() }
    }

    suspend fun saveUser(user: User) {
        db.collection("users").document(user.userId).set(user).await()
    }

    suspend fun getUser(): User? {
        val uid = auth.currentUser?.uid ?: return null
        return db.collection("users").document(uid).get().await().toObject(User::class.java)
    }
}
