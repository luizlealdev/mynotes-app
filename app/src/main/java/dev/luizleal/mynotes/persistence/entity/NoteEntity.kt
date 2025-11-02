package dev.luizleal.mynotes.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.luizleal.mynotes.util.Constants
import java.util.Date

@Entity(
    tableName = Constants.NOTE_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["folder_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("folder_id")]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "updated_at") val updatedAt: Date = Date(),
    @ColumnInfo(name = "folder_id") val folderId: Long? = null,
)
