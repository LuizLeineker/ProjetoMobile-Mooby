package com.example.mooby.data.dto

data class MetaDTO(
    var id: String = "",
    var userId: String = "",
    var title: String = "",
    var targetValue: Double = 0.0,
    var progress: Double = 0.0,
    var deadline: Long = 0L
)
