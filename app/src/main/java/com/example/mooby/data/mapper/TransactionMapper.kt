package com.example.mooby.data.mapper

import com.example.mooby.data.dto.TransactionDTO
import com.example.mooby.data.entity.TransactionEY

fun TransactionDTO.toEntity(): TransactionEY {
    return TransactionEY(
        id = this.id,
        userId = this.userId,
        type = this.type,
        category = this.category,
        amount = this.amount, // DTO -> Entity
        timestamp = this.timestamp, // DTO -> Entity
        description = this.description
    )
}

fun TransactionEY.toDTO(): TransactionDTO {
    return TransactionDTO(
        id = this.id,
        userId = this.userId,
        type = this.type,
        category = this.category,
        amount = this.amount, // Entity -> DTO
        timestamp = this.timestamp, // Entity -> DTO
        description = this.description
    )
}
