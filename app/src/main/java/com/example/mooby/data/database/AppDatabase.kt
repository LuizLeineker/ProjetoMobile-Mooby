package com.example.mooby.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mooby.data.dao.MetaDAO
import com.example.mooby.data.dao.TransactionDAO
import com.example.mooby.data.dao.UserDAO
import com.example.mooby.data.entity.Meta
import com.example.mooby.data.entity.TransactionEY
import com.example.mooby.data.entity.User

@Database(
    entities = [User::class, Meta::class, TransactionEY::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun metaDAO(): MetaDAO
    abstract fun transactionDAO(): TransactionDAO
}
