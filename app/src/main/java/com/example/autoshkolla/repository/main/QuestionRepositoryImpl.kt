package com.example.autoshkolla.repository.main

import com.example.autoshkolla.model.QuestionModel
import com.example.autoshkolla.repository.dataSource.QuestionLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val localDataSource: QuestionLocalDataSource
) : QuestionRepository {

    override fun getQuestionsFromDB(): Flow<List<QuestionModel>> {
        return localDataSource.getQuestionsFromDB()
    }

    override suspend fun insertQuestionToDB(question: QuestionModel) {
       return localDataSource.insertQuestionToDB(question)
    }

    override suspend fun deleteQuestionFromDB(id: Int) {
        return localDataSource.deleteQuestionFromDB(id)
    }

}