package dev.luizleal.mynotes.persistence.mapper

import dev.luizleal.mynotes.domain.model.Folder
import dev.luizleal.mynotes.persistence.entity.FolderEntity
import java.time.ZoneId
import java.util.Date

fun FolderEntity.toModel() = Folder(
    id = id,
    name = name,
    color = color,
    createdAt = createdAt
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
)

fun Folder.toEntity() = FolderEntity(
    id = id,
    name = name,
    color = color,
    createdAt = createdAt
        ?.atZone(ZoneId.systemDefault())
        ?.toInstant()
        ?.let { Date.from(it) } ?: Date()
)