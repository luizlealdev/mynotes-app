package dev.luizleal.mynotes.persistence.local.converters

import androidx.room.TypeConverter
import dev.luizleal.mynotes.domain.model.FolderColor

class FolderColorConverter {

    @TypeConverter
    fun fromFolderColor(color: FolderColor) = color.name

    @TypeConverter
    fun toFolderColor(value: String) = FolderColor.valueOf(value)
}