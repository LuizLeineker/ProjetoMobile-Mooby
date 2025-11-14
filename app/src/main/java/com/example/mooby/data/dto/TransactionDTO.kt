package com.example.mooby.data.dto

data class TransactionDTO(
    var id: String = "",
    var userId: String = "",
    var type: String = "",
    var category: String = "",
    var amount: Double = 0.0,
    var timestamp: Long = 0L,
    var description: String = ""
)
