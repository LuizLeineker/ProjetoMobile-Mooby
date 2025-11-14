package com.example.mooby.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(tableName = Helper.TABLE_META)
data class Meta(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null, // null para gerar automático
    val userId: String = "",
    val title: String = "",
    val targetValue: Double = 0.0,
    val progress: Double = 0.0,
    val deadline: Long = 0L
)
