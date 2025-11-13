package com.example.mooby.model.dao

import androidx.room.*
import com.example.mooby.model.entity.User
import com.example.mooby.model.dto.TypeSummary  //criado excluisvamente para o relatorio mensal
import com.example.mooby.utils.Helper
import kotlinx.coroutines.flow.Flow

//CRUD
@Dao
interface TransactionDao {
    // C - CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    // R - READ  (Todos os gastos por ordem de data)
    @Query("SELECT * FROM ${Helper.TABLE_TRANSACTION} WHERE ${Helper.USER_ID} = :userId ORDER BY ${Helper.DATA} DESC")
    fun getTransactionsByUserId(userId: Int): Flow<List<Transaction>>

    // U - UPDATE
    @Update
    suspend fun updateTransaction(transaction: Transaction)

    // D - DELETE
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)


    // S - SEARCH - Busca por categoria
    @Query("SELECT * FROM ${Helper.TABLE_TRANSACTION} WHERE ${Helper.USER_ID} = :userId AND ${Helper.CATEGORY} LIKE '%' || :category || '%' ORDER BY ${Helper.DATA} DESC")
    fun getTransactionsByCategory(userId: Int, category: String): Flow<List<Transaction>>

    // Relatorio Finaceiro Mensal
    // Data precisa estar em ordem: "yyyy-MM-dd"
    @Query("""
        SELECT ${Helper.TIPO} AS tipo, SUM(${Helper.VALUE}) AS total
        FROM ${Helper.TABLE_TRANSACTION}
        WHERE ${Helper.USER_ID} = :userId AND substr(${Helper.DATA}, 1, 7) = :monthFormat
        GROUP BY ${Helper.TIPO}
    """)
    // Foi necessario implementar uma dataclass - DTO (Objeto de transferencia de dados)
    suspend fun getMonthlySummaryByType(userId: Int, monthFormat: String): List<TypeSummary>


}