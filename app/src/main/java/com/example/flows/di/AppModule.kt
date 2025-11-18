package com.example.flows.di

import android.content.Context
import androidx.room.Room
import com.example.flows.data.AppDatabase
import com.example.flows.data.TodoDao
import com.example.flows.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todos"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTodoDao(db: AppDatabase): TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepository(dao)
    }
}