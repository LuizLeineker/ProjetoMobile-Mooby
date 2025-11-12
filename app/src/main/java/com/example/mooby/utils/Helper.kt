package com.example.mooby.utils

abstract class Helper {
    companion object {


        const val DB_NAME = "mooby_db"

        // Users
        const val TABLE_USERS = "users_tb"
        const val USER_ID = "userId"
        const val USERNAME = "username"
        const val EMAIL = "email"

        // Transaction
        const val TABLE_TRANSACTION = "transaction_tb"
        const val TRANSACTION_ID = "transactionId"
        const val TIPO = "tipo"
        const val CATEGORY = "category"
        const val VALUE = "value"
        const val DATA =  "data"
        const val DESCRIPTION = "description"

        // Meta
        const val TABLE_META = "meta_tb"
        const val META_ID = "metaId"
        const val TITLE = "title"
        const val VALUEMETA = "valueMeta"
        const val PROGRESS = "progress"
        const val TERM = "term"



    }
}