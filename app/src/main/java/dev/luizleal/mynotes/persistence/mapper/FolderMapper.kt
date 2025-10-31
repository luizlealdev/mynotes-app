package dev.luizleal.mynotes.persistence.mapper

import dev.luizleal.mynotes.domain.model.Folder
import dev.luizleal.mynotes.persistence.entity.FolderEntity
import java.sql.Date
import java.time.ZoneId

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
    createdAt = Date.from(
        createdAt
            ?.atZone(ZoneId.systemDefault())
            ?.toInstant()
    )
)