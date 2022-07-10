package com.example.autoshkolla.repository.dataSourceImpl

import com.example.autoshkolla.database.dao.QuestionDao
import com.example.autoshkolla.model.QuestionModel
import com.example.autoshkolla.repository.dataSource.QuestionLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionLocalDataSourceImpl @Inject constructor(
    private val questionDao: QuestionDao
) : QuestionLocalDataSource {

    override fun getQuestionsFromDB(): Flow<List<QuestionModel>> {
        return questionDao.getQuestionsFromDB()
    }

    override suspend fun insertQuestionToDB(question: QuestionModel) {
        return questionDao.insertQuestionToDB(question)
    }

    override suspend fun deleteQuestionFromDB(id: Int) {
        return questionDao.deleteQuestionFromDB(id)
    }
}