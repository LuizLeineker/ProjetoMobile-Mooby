package com.example.mooby.data.repository

import com.example.mooby.data.entity.Meta
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

object FirebaseConfig {
    private const val USE_LOCAL_EMULATOR = true
    val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance().apply {
            if (USE_LOCAL_EMULATOR) {
                useEmulator("10.0.2.2", 8080)
            }
        }
    }
}

class Repository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseConfig.db
) {

    // Transações
    suspend fun saveTransaction(t: TransactionEY) {
        val id = if (t.id.isNullOrEmpty()) db.collection("transactions").document().id else t.id
        db.collection("transactions").document(id).set(t.copy(id = id)).await()
    }

    fun getTransactions() = callbackFlow {
        val uid = auth.currentUser?.uid ?: ""
        val listener = db.collection("transactions")
            .whereEqualTo("userId", uid)
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                trySend(snapshot?.toObjects(TransactionEY::class.java) ?: emptyList())
            }
        awaitClose { listener.remove() }
    }

    // Metas
    suspend fun saveMeta(meta: Meta) {
        // Aqui id pode ser Int ou String. Para Firebase, vamos gerar String única:
        val id = meta.id?.toString()?.takeIf { it.isNotEmpty() } ?: db.collection("metas").document().id
        db.collection("metas").document(id).set(meta.copy(id = id.toIntOrNull())).await()
    }

    fun getMetas() = callbackFlow<List<Meta>> {
        val uid = auth.currentUser?.uid ?: ""
        val listener = db.collection("metas")
            .whereEqualTo("userId", uid)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                trySend(snapshot?.toObjects(Meta::class.java) ?: emptyList())
            }
        awaitClose { listener.remove() }
    }

    // Usuário
    suspend fun saveUser(user: User) {
        db.collection("users").document(user.userId).set(user).await()
    }

    suspend fun getUser(): User? {
        val uid = auth.currentUser?.uid ?: return null
        return db.collection("users").document(uid).get().await().toObject(User::class.java)
    }
}
