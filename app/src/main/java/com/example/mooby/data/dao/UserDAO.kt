package com.example.mooby.data.dao

import androidx.room.*
import com.example.mooby.data.entity.User
import com.example.mooby.utils.Helper

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM ${Helper.TABLE_USERS} WHERE ${Helper.USER_ID} = :uid")
    suspend fun getUserById(uid: String): User?
}
