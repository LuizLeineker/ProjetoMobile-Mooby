package com.example.mooby.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity (tableName = Helper.TABLE_META)
data class Meta(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val userId: Int,
    val title: String,
    val valueMeta: Double,
    val progress: Double,
    val term: Double
)