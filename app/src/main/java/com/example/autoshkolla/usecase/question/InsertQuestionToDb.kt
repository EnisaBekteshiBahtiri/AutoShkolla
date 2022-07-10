package com.example.autoshkolla.usecase.question

import com.example.autoshkolla.model.QuestionModel
import com.example.autoshkolla.repository.main.QuestionRepository
import javax.inject.Inject

class InsertQuestionToDb @Inject constructor(private val questionRepository: QuestionRepository) {
    suspend fun execute(questionModel: QuestionModel): Unit =
        questionRepository.insertQuestionToDB(questionModel)
}