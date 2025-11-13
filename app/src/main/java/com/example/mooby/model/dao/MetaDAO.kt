package com.example.mooby.model.dao

import androidx.room.*
import com.example.mooby.model.entity.Meta
import com.example.mooby.utils.Helper
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDao {
    // C - CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(meta: Meta): Long

    // R - READ - LIST
    @Query("SELECT * FROM ${Helper.TABLE_META} WHERE ${Helper.USER_ID} = :userId ORDER BY ${Helper.TERM} ASC")
    fun getMetasByUserId(userId: Int): Flow<List<Meta>>

    // U - UPDATE
    @Update
    suspend fun updateMeta(meta: Meta)

    // D - DELETE
    @Delete
    suspend fun deleteMeta(meta: Meta)


}
