package com.example.mooby.data.dao

import androidx.room.*
import com.example.mooby.data.entity.Meta
import com.example.mooby.utils.Helper
import kotlinx.coroutines.flow.Flow

@Dao
interface MetaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(meta: Meta): Long

    @Query("SELECT * FROM ${Helper.TABLE_META} WHERE ${Helper.USER_ID} = :userId ORDER BY ${Helper.TERM} ASC")
    fun getMetasByUserId(userId: String): Flow<List<Meta>>

    @Update
    suspend fun updateMeta(meta: Meta)

    @Delete
    suspend fun deleteMeta(meta: Meta)
}
