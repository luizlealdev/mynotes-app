package dev.luizleal.mynotes.domain.model

import java.time.LocalDateTime

data class Folder(
    val id: Long = 0,
    val name: String,
    val color: FolderColor = FolderColor.BLUE,
    val createdAt: LocalDateTime? = null
)
