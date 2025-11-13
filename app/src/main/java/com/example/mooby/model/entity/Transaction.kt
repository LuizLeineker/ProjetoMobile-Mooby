package com.example.mooby.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(
    tableName = Helper.TABLE_TRANSACTION,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [Helper.USER_ID])]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Helper.TRANSACTION_ID)
    val id: Int = 0,

    @ColumnInfo(name = Helper.USER_ID)
    val userId: Int,

    @ColumnInfo(name = Helper.TIPO)
    val tipo: String,

    @ColumnInfo(name = Helper.CATEGORY)
    val category: String,

    @ColumnInfo(name = Helper.VALUE)
    val value: Double,

    @ColumnInfo(name = Helper.DATA)
    val data: String,

    @ColumnInfo(name = Helper.DESCRIPTION)
    val description: String
)