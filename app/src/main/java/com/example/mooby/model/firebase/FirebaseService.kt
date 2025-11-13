package com.example.mooby.model.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseService {
    private val db = FirebaseFirestore.getInstance()

    fun getUsersCollection() = db.collection("users")
    fun getMetasCollection() = db.collection("metas")
    fun getTransactionsCollection() = db.collection("transactions")
}
