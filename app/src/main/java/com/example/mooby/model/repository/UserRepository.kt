package com.example.mooby.model.repository

import com.example.mooby.model.dao.UserDAO
import com.example.mooby.model.dto.UserDTO
import com.example.mooby.model.entity.User
import com.example.mooby.model.mapper.toDTO
import com.example.mooby.model.mapper.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val dao: UserDAO,
    private val firestore: FirebaseFirestore
) {
    //CRUD
    private val collection = firestore.collection("users")

    suspend fun insertLocal(user: User) = dao.insertUser(user)

    suspend fun updateLocal(user: User) = dao.update(user)

    suspend fun deleteLocal(user: User) = dao.delete(user)

    suspend fun getUserById(id: Int) = dao.getUserById(id)

    // FIREBASE
    suspend fun syncToFirebase(user: User) {
        val dto = user.toDTO()
        collection.document(dto.id.toString()).set(dto).await()
    }

    suspend fun fetchFromFirebase(): List<User> {
        val snapshot = collection.get().await()
        return snapshot.documents
            .mapNotNull { it.toObject(UserDTO::class.java)?.toEntity() }
    }
}