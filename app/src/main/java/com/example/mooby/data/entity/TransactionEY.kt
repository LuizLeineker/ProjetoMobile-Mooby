package com.example.mooby.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(tableName = Helper.TABLE_TRANSACTION)
data class TransactionEY(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String, // Firebase UID
    val type: String,
    val category: String,
    val value: Double,
    val date: Long,
    val description: String?
)
