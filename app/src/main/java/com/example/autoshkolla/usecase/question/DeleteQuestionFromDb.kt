package com.example.autoshkolla.usecase.question

import com.example.autoshkolla.repository.main.QuestionRepository
import javax.inject.Inject

class DeleteQuestionFromDb @Inject constructor(private val questionRepository: QuestionRepository) {
    suspend fun execute(id: Int) : Unit = questionRepository.deleteQuestionFromDB(id)
}