package com.example.autoshkolla.repository.main

import com.example.autoshkolla.model.QuestionModel
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestionsFromDB() : Flow<List<QuestionModel>>
    suspend fun insertQuestionToDB(question: QuestionModel)
    suspend fun deleteQuestionFromDB(id: Int)
}