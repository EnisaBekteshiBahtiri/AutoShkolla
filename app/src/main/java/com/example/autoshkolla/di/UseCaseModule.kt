package com.example.autoshkolla.di

import com.example.autoshkolla.repository.main.QuestionRepository
import com.example.autoshkolla.repository.main.RuleRepository
import com.example.autoshkolla.repository.main.SignRepository
import com.example.autoshkolla.usecase.question.DeleteQuestionFromDb
import com.example.autoshkolla.usecase.question.GetQuestionFromDb
import com.example.autoshkolla.usecase.question.InsertQuestionToDb
import com.example.autoshkolla.usecase.rules.DeleteRulesFromDbUseCase
import com.example.autoshkolla.usecase.rules.GetRulesFromDBUseCase
import com.example.autoshkolla.usecase.rules.InsertRuleToDbUseCase
import com.example.autoshkolla.usecase.signs.DeleteSignFromDbUseCase
import com.example.autoshkolla.usecase.signs.GetSignsFromDbUseCase
import com.example.autoshkolla.usecase.signs.InsertSignToDbUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetRulesFromDbUseCase(
        ruleRepository: RuleRepository
    ): GetRulesFromDBUseCase {
        return GetRulesFromDBUseCase(ruleRepository)
    }

    @Singleton
    @Provides
    fun provideInsertRuleToDbUseCase(
        ruleRepository: RuleRepository
    ): InsertRuleToDbUseCase {
        return InsertRuleToDbUseCase(ruleRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteRuleToDbUseCase(
        ruleRepository: RuleRepository
    ): DeleteRulesFromDbUseCase {
        return DeleteRulesFromDbUseCase(ruleRepository)
    }

    @Singleton
    @Provides
    fun provideGetSignsFromDbUseCase(
        signRepository: SignRepository
    ): GetSignsFromDbUseCase {
        return GetSignsFromDbUseCase(signRepository)
    }

    @Singleton
    @Provides
    fun provideInsertSignToDbUseCase(
        signRepository: SignRepository
    ): InsertSignToDbUseCase {
        return InsertSignToDbUseCase(signRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSignFromDbUseCase(
        signRepository: SignRepository
    ): DeleteSignFromDbUseCase {
        return DeleteSignFromDbUseCase(signRepository)
    }

    @Singleton
    @Provides
    fun provideGetQuestionsFromDbUseCase(
        questionRepository: QuestionRepository
    ): GetQuestionFromDb {
        return GetQuestionFromDb(questionRepository)
    }

    @Singleton
    @Provides
    fun provideInsertQuestionToDbUseCase(
        questionRepository: QuestionRepository
    ): InsertQuestionToDb {
        return InsertQuestionToDb(questionRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteQuestionFromDbUseCase(
        questionRepository: QuestionRepository
    ): DeleteQuestionFromDb {
        return DeleteQuestionFromDb(questionRepository)
    }
}