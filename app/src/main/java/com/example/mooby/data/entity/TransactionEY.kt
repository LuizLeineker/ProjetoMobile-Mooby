package com.example.mooby.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEY(
    @PrimaryKey val id: String = "",
    val userId: String = "",
    val type: String = "",
    val category: String = "",
    val amount: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis(),
    val description: String = ""
)
