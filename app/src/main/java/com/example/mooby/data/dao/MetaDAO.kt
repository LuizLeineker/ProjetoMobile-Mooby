package com.example.mooby.data.dao

import androidx.room.*
import com.example.mooby.data.entity.Meta
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(meta: Meta): Long

    @Query("SELECT * FROM ${com.example.mooby.utils.Helper.TABLE_META} WHERE ${com.example.mooby.utils.Helper.USER_ID} = :userId ORDER BY ${com.example.mooby.utils.Helper.TERM} ASC")
    fun getMetasByUserId(userId: String): Flow<List<Meta>>

    @Update
    suspend fun updateMeta(meta: Meta)

    @Delete
    suspend fun deleteMeta(meta: Meta)
}
