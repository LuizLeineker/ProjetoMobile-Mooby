package com.example.mooby.data.dao

import androidx.room.*
import com.example.mooby.data.entity.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM ${com.example.mooby.utils.Helper.TABLE_USERS} WHERE ${com.example.mooby.utils.Helper.USER_ID} = :uid")
    suspend fun getUserById(uid: String): User?
}
