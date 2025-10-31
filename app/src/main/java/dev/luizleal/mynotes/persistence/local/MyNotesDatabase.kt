package dev.luizleal.mynotes.persistence.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.luizleal.mynotes.persistence.entity.FolderEntity
import dev.luizleal.mynotes.persistence.entity.NoteEntity
import dev.luizleal.mynotes.persistence.local.converters.DateConverter
import dev.luizleal.mynotes.persistence.local.converters.FolderColorConverter
import dev.luizleal.mynotes.persistence.local.dao.FolderDao
import dev.luizleal.mynotes.persistence.local.dao.NoteDao

@Database(
    entities = [NoteEntity::class, FolderEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    DateConverter::class, FolderColorConverter::class
)
abstract class MyNotesDatabase : RoomDatabase(){

    abstract fun noteDao(): NoteDao
    abstract fun folderDao(): FolderDao
}