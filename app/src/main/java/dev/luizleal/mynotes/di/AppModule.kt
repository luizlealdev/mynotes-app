package dev.luizleal.mynotes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.luizleal.mynotes.domain.repository.FolderRepository
import dev.luizleal.mynotes.domain.repository.NoteRepository
import dev.luizleal.mynotes.persistence.local.MyNotesDatabase
import dev.luizleal.mynotes.persistence.repository.FolderRepositoryImpl
import dev.luizleal.mynotes.persistence.repository.NoteRepositoryImpl
import dev.luizleal.mynotes.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyNotesDatabase(
        @ApplicationContext context: Context
    ) : MyNotesDatabase = Room.databaseBuilder(
        context,
        MyNotesDatabase::class.java,
        Constants.MYNOTES_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(database: MyNotesDatabase): NoteRepository =
        NoteRepositoryImpl(database)

    @Provides
    @Singleton
    fun provideFolderRepository(database: MyNotesDatabase) : FolderRepository =
        FolderRepositoryImpl(database)

}