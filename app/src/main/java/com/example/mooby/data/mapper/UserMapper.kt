package com.example.mooby.data.mapper

import com.example.mooby.data.dto.UserDTO
import com.example.mooby.data.entity.User

fun UserDTO.toEntity(): User {
    return User(
        userId = this.userId,
        name = this.name,
        email = this.email,
        saldoInicial = this.saldoInicial
    )
}

fun User.toDTO(): UserDTO {
    return UserDTO(
        userId = this.userId,
        name = this.name,
        email = this.email,
        saldoInicial = this.saldoInicial
    )
}
