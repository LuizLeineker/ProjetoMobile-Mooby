package com.example.mooby.model.mapper

import com.example.mooby.model.dto.TransactionDTO
import com.example.mooby.model.entity.TransactionEY

// Responsavel por conectar os valores da firebase (DTO) com os ROM (entity)
// String -> Int
fun TransactionEY.toDTO(): TransactionDTO {
    return TransactionDTO(
        id = this.id.toString(),
        userId = this.userId.toString(),
        tipo = this.tipo,
        category = this.category,
        value = this.value,
        data = this.data,
        description = this.description
    )
}

fun TransactionDTO.toEntity(): TransactionEY {
    return TransactionEY(
        id = this.id.toIntOrNull() ?: 0,
        userId = this.userId.toIntOrNull() ?: 0,
        tipo = this.tipo,
        category = this.category,
        value = this.value,
        data = this.data,
        description = this.description
    )
}