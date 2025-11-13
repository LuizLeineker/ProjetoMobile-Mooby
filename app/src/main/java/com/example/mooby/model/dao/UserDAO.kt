package com.example.mooby.model.dao

import androidx.room.*
import com.example.mooby.model.entity.User
import com.example.mooby.utils.Helper

// Para o user será feito utilizado apenas registro de novos usuario e busca para o login!
@Dao
interface UserDAO {


    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)


    // Search
    @Query("SELECT * FROM ${Helper.TABLE_USERS} WHERE ${Helper.USER_ID} = :id")
    suspend fun getUserById(id: Int): User?


}
