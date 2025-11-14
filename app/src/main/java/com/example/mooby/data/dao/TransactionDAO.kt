package com.example.mooby.data.dao

import androidx.room.*
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.dto.TypeSummary
import com.example.mooby.utils.Helper
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEY): Long

    @Query("SELECT * FROM ${Helper.TABLE_TRANSACTION} WHERE ${Helper.USER_ID} = :userId ORDER BY ${Helper.DATA} DESC")
    fun getTransactionsByUserId(userId: String): Flow<List<TransactionEY>>

    @Update
    suspend fun updateTransaction(transaction: TransactionEY)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEY)

    @Query("SELECT * FROM ${Helper.TABLE_TRANSACTION} WHERE ${Helper.USER_ID} = :userId AND ${Helper.CATEGORY} LIKE '%' || :category || '%' ORDER BY ${Helper.DATA} DESC")
    fun getTransactionsByCategory(userId: String, category: String): Flow<List<TransactionEY>>

    @Query("""
        SELECT ${Helper.TIPO} AS type, SUM(${Helper.VALUE}) AS total
        FROM ${Helper.TABLE_TRANSACTION}
        WHERE ${Helper.USER_ID} = :userId AND strftime('%Y-%m', datetime(${Helper.DATA} / 1000, 'unixepoch')) = :monthFormat
        GROUP BY ${Helper.TIPO}
    """)
    suspend fun getMonthlySummaryByType(userId: String, monthFormat: String): List<TypeSummary>
}
