package dev.luizleal.mynotes.persistence.mapper

import dev.luizleal.mynotes.domain.model.Note
import dev.luizleal.mynotes.persistence.entity.NoteEntity
import java.time.ZoneId
import java.util.Date

fun NoteEntity.toModel() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
        .toInstant() // converte Date → Instant (UTC)
        .atZone(ZoneId.systemDefault()) // ajusta para o fuso local
        .toLocalDateTime(), // Remove o fuso, ficando só data + hora
    updatedAt = updatedAt
        ?.toInstant() //Pode ser nulo, então safe call
        ?.atZone(ZoneId.systemDefault()) // ajusta para o fuso
        ?.toLocalDateTime(), //Converte para LocalDateTime
    folderId = folderId
)

fun Note.toEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
        ?.atZone(ZoneId.systemDefault())
        ?.toInstant()
        ?.let { Date.from(it) } ?: Date(),
    updatedAt = updatedAt
        ?.atZone(ZoneId.systemDefault())
        ?.toInstant()
        ?.let { Date.from(it) },
    folderId = folderId
)
