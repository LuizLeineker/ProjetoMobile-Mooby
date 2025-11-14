package com.example.mooby.data.repository

import com.example.mooby.data.dao.MetaDAO
import com.example.mooby.data.dao.TransactionDAO
import com.example.mooby.data.dao.UserDAO
import com.example.mooby.data.entity.Meta
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class Repository(
    private val userDao: UserDAO,
    private val metaDao: MetaDAO,
    private val transactionDao: TransactionDAO,
) {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun uid(): String = auth.currentUser?.uid ?: ""

    // ------------------ USERS ---------------------

    suspend fun saveUser(user: User) {
        userDao.insertUser(user)

        firestore.collection("users")
            .document(uid())
            .set(user)
    }

    suspend fun getUser(): User? =
        userDao.getUserById(uid())

    // ------------------ METAS ---------------------

    fun getMetas(): Flow<List<Meta>> =
        metaDao.getMetasByUserId(uid())

    suspend fun saveMeta(meta: Meta) {
        metaDao.insertMeta(meta)

        firestore.collection("users")
            .document(uid())
            .collection("goals")
            .add(meta)
    }

    // ------------------ TRANSAÇÕES ----------------

    fun getTransactions(): Flow<List<TransactionEY>> =
        transactionDao.getTransactionsByUserId(uid())

    suspend fun saveTransaction(t: TransactionEY) {
        transactionDao.insertTransaction(t)

        firestore.collection("users")
            .document(uid())
            .collection("transactions")
            .add(t)
    }
}
