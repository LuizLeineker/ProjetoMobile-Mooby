package com.example.mooby.model.repository


import com.example.mooby.model.dao.MetaDAO
import com.example.mooby.model.dto.MetaDTO
import com.example.mooby.model.entity.Meta
import com.example.mooby.model.mapper.toDTO
import com.example.mooby.model.mapper.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MetaRepository(private val dao: MetaDAO,  private val firestore: FirebaseFirestore){

    private val collection = firestore.collection("metas")

    // CRUD
    suspend fun insertLocal(meta: Meta) = dao.insertMeta(meta)
    suspend fun updateLocal(meta: Meta) = dao.updateMeta(meta)
    suspend fun deleteLocal(meta: Meta) = dao.deleteMeta(meta)
    fun getMetasByUser(userId: Int) = dao.getMetasByUserId(userId)

    // FIREBASE
    suspend fun syncToFirebase(meta: Meta) {
        val dto = meta.toDTO()
        collection.document(dto.id).set(dto).await()
    }

    suspend fun fetchFromFirebase(userId: Int): List<Meta> {
        val snapshot = collection
            .whereEqualTo("userId", userId.toString())
            .get()
            .await()

        return snapshot.documents.mapNotNull { it.toObject(MetaDTO::class.java)?.toEntity() }
    }
}