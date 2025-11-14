package com.example.mooby.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(tableName = Helper.TABLE_USERS)
data class User(
    @PrimaryKey
    val userId: String,
    val name: String,
    val email: String,
    val saldoInicial: Double = 0.0
)
