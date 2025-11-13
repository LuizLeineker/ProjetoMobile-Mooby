package com.example.mooby.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mooby.utils.Helper

@Entity(
    tableName = Helper.TABLE_META,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = [Helper.USER_ID],
            childColumns = [Helper.USER_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [Helper.USER_ID])]
)
data class Meta(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Helper.META_ID)
    val id: Int = 0,

    @ColumnInfo(name = Helper.USER_ID)
    val userId: Int,

    @ColumnInfo(name = Helper.TITLE)
    val title: String,

    @ColumnInfo(name = Helper.VALUEMETA)
    val valueMeta: Double,

    @ColumnInfo(name = Helper.PROGRESS)
    val progress: Double,

    @ColumnInfo(name = Helper.TERM)
    val term: Double
)
