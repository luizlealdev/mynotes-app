package dev.luizleal.mynotes.persistence.entity

import dev.luizleal.mynotes.domain.model.FolderColor
import java.util.Date

data class FolderEntity(
    val id: Long = 0,
    val name: String,
    val color: FolderColor = FolderColor.BLUE,
    val createdAt: Date = Date(),
    val notes: List<NoteEntity>
)