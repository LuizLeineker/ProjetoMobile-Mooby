package com.example.mooby.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity (tableName = Helper.TABLE_USERS)
data class User(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val valueInitial: Double
)