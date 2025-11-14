package com.example.mooby.data.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseService {
    private val db = FirebaseFirestore.getInstance()

    fun getUsersCollection() = db.collection("users")
    fun getMetasCollection() = db.collection("metas")
    fun getTransactionsCollection() = db.collection("transactions")
}
