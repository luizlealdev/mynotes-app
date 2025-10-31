package dev.luizleal.mynotes.domain.model

import java.time.LocalDateTime

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val folderId: Long? = null,
)
