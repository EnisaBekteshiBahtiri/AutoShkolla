package com.example.autoshkolla.di

import android.app.Application
import androidx.room.Room
import com.example.autoshkolla.database.AppDatabase
import com.example.autoshkolla.database.dao.QuestionDao
import com.example.autoshkolla.database.dao.RuleDao
import com.example.autoshkolla.database.dao.SignDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DATABASE_NAME = "AutoShkollaDB"

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRulesDao(appDatabase: AppDatabase): RuleDao {
        return appDatabase.getRulesDao()
    }

    @Singleton
    @Provides
    fun provideSignDao(appDatabase: AppDatabase): SignDao {
        return appDatabase.getSignDao()
    }

    @Singleton
    @Provides
    fun provideQuestionDao(appDatabase: AppDatabase): QuestionDao {
        return appDatabase.getQuestionDao()
    }

}