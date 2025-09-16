package com.example.introtocompose.di

import android.content.Context
import androidx.room.Room
import com.example.introtocompose.data.NoteDatabase
import com.example.introtocompose.data.NoteDatabaseDao
import com.example.introtocompose.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

//    @Singleton
//    @Provides
//    fun provideNoteRepository(noteDao: NoteDatabaseDao): NoteRepository {
//        return NoteRepository(noteDao)
//    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_db"
        ).fallbackToDestructiveMigration(false).build()

}
