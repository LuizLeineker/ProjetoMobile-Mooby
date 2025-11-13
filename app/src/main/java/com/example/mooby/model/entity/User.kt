package com.example.mooby.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(tableName = Helper.TABLE_USERS)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Helper.USER_ID)
    val id: Int = 0,

    @ColumnInfo(name = Helper.USERNAME)
    val name: String,

    @ColumnInfo(name = Helper.EMAIL)
    val email: String,

    @ColumnInfo(name = Helper.VALUE_INITIAL)
    val valueInitial: Double
)