package com.example.mooby.model.dto

data class TransactionDTO(
    var id: String = "",
    var userId: String = "",
    var tipo: String = "",
    var category: String = "",
    var value: Double = 0.0,
    var data: String = "",
    var description: String = ""
)