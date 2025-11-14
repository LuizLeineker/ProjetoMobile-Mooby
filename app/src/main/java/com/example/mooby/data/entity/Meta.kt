package com.example.mooby.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(tableName = Helper.TABLE_META)
data class Meta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String, // Firebase UID
    val title: String,
    val targetValue: Double,
    val progress: Double,
    val deadline: Long
)
