package com.example.autoshkolla.repository.dataSource

import com.example.autoshkolla.model.QuestionModel
import kotlinx.coroutines.flow.Flow

interface QuestionLocalDataSource {
    fun getQuestionsFromDB() : Flow<List<QuestionModel>>
    suspend fun insertQuestionToDB(question: QuestionModel)
    suspend fun deleteQuestionFromDB(id: Int)

}