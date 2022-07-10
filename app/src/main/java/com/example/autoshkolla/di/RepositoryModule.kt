package com.example.autoshkolla.di

import com.example.autoshkolla.repository.dataSource.QuestionLocalDataSource
import com.example.autoshkolla.repository.dataSource.RulesLocalDataSource
import com.example.autoshkolla.repository.dataSource.SignsLocalDataSource
import com.example.autoshkolla.repository.main.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRulesRepository(
        localDataSource: RulesLocalDataSource
    ): RuleRepository {
        return RuleRepositoryImpl(
            localDataSource
        )
    }

    @Provides
    @Singleton
    fun providesSignsRepository(
        localDataSource: SignsLocalDataSource
    ): SignRepository {
        return SignRepositoryImpl(
            localDataSource
        )
    }

    @Provides
    @Singleton
    fun providesQuestionsRepository(
        localDataSource: QuestionLocalDataSource
    ): QuestionRepository {
        return QuestionRepositoryImpl(
            localDataSource
        )
    }
}