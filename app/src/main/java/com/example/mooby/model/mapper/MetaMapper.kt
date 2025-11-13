package com.example.mooby.model.mapper

import com.example.mooby.model.dto.MetaDTO
import com.example.mooby.model.entity.Meta

// Responsavel por conectar os valores da firebase (DTO) com os ROM (entity)
// String -> Int
fun Meta.toDTO(): MetaDTO {
    return MetaDTO(
        id = this.id.toString(),
        userId = this.userId.toString(),
        title = this.title,
        valueMeta = this.valueMeta,
        progress = this.progress,
        term = this.term
    )
}

fun MetaDTO.toEntity(): Meta {
    return Meta(
        id = this.id.toIntOrNull() ?: 0,
        userId = this.userId.toIntOrNull() ?: 0,
        title = this.title,
        valueMeta = this.valueMeta,
        progress = this.progress,
        term = this.term
    )
}