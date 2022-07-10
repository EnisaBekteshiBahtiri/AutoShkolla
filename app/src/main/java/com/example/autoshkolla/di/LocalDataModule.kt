package com.example.autoshkolla.di

import com.example.autoshkolla.database.dao.QuestionDao
import com.example.autoshkolla.database.dao.RuleDao
import com.example.autoshkolla.database.dao.SignDao
import com.example.autoshkolla.repository.dataSource.QuestionLocalDataSource
import com.example.autoshkolla.repository.dataSource.RulesLocalDataSource
import com.example.autoshkolla.repository.dataSource.SignsLocalDataSource
import com.example.autoshkolla.repository.dataSourceImpl.QuestionLocalDataSourceImpl
import com.example.autoshkolla.repository.dataSourceImpl.RulesLocalDataSourceImpl
import com.example.autoshkolla.repository.dataSourceImpl.SignsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideRulesLocalDataSource(ruleDao: RuleDao): RulesLocalDataSource {
        return RulesLocalDataSourceImpl(ruleDao)
    }
    @Singleton
    @Provides
    fun provideSignsLocalDataSource(signDao: SignDao): SignsLocalDataSource {
        return SignsLocalDataSourceImpl(signDao)
    }
    @Singleton
    @Provides
    fun provideQuestionsLocalDataSource(questionDao: QuestionDao): QuestionLocalDataSource {
        return QuestionLocalDataSourceImpl(questionDao)
    }
}