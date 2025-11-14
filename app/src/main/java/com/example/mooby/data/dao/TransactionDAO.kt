package com.example.mooby.data.dao

import androidx.room.*
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.dto.TypeSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEY): Long

    @Query("""
        SELECT * FROM ${com.example.mooby.utils.Helper.TABLE_TRANSACTION} 
        WHERE ${com.example.mooby.utils.Helper.USER_ID} = :userId 
        ORDER BY timestamp DESC
    """)
    fun getTransactionsByUserId(userId: String): Flow<List<TransactionEY>>

    @Update
    suspend fun updateTransaction(transaction: TransactionEY)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEY)

    @Query("""
        SELECT ${com.example.mooby.utils.Helper.TIPO} AS type, SUM(amount) AS total
        FROM ${com.example.mooby.utils.Helper.TABLE_TRANSACTION}
        WHERE ${com.example.mooby.utils.Helper.USER_ID} = :userId 
        AND strftime('%Y-%m', datetime(timestamp / 1000, 'unixepoch')) = :monthFormat
        GROUP BY ${com.example.mooby.utils.Helper.TIPO}
    """)
    suspend fun getMonthlySummaryByType(userId: String, monthFormat: String): List<TypeSummary>
}
