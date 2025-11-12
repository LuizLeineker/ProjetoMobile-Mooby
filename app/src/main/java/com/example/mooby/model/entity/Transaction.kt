package com.example.mooby.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity (tableName = Helper.TABLE_TRANSACTION)
data class Transaction(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val userId: Int,
    val tipo: String,
    val category: String,
    val value: Double,
    val data: String,
    val description: String
)