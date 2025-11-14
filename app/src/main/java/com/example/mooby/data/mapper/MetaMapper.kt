package com.example.mooby.data.mapper

import com.example.mooby.data.dto.MetaDTO
import com.example.mooby.data.entity.Meta

fun MetaDTO.toEntity(): Meta {
    return Meta(
        id = this.id.toIntOrNull() ?: 0,
        userId = this.userId,
        title = this.title,
        targetValue = this.targetValue, // DTO -> Entity
        progress = this.progress,
        deadline = this.deadline // DTO -> Entity
    )
}

fun Meta.toDTO(): MetaDTO {
    return MetaDTO(
        id = this.id.toString(),
        userId = this.userId,
        title = this.title,
        targetValue = this.targetValue, // Entity -> DTO
        progress = this.progress,
        deadline = this.deadline // Entity -> DTO
    )
}
