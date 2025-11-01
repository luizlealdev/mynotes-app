package dev.luizleal.mynotes.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.luizleal.mynotes.domain.model.FolderColor
import dev.luizleal.mynotes.util.Constants
import java.util.Date

@Entity(tableName = Constants.FOLDER_TABLE)
data class FolderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val color: FolderColor = FolderColor.BLUE,
    @ColumnInfo(name = "created_at") val createdAt: Date = Date()
)