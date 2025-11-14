package com.example.mooby.data.mapper

import com.example.mooby.data.dto.UserDTO
import com.example.mooby.data.entity.User

// Responsavel por conectar os valores da firebase (DTO) com os ROM (entity)
// String -> Int

fun User.toDTO(): UserDTO {
    return UserDTO(
        id = this.id.toString(),
        name = this.name,
        email = this.email,
        valueInitial = this.valueInitial
    )
}

fun UserDTO.toEntity(): User {
    return User(
        id = this.id.toIntOrNull() ?: 0,
        name = this.name,
        email = this.email,
        valueInitial = this.valueInitial
    )
}
