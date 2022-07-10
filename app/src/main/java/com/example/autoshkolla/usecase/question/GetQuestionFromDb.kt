package com.example.autoshkolla.usecase.question

import com.example.autoshkolla.model.QuestionModel
import com.example.autoshkolla.repository.main.QuestionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionFromDb @Inject constructor(private val questionRepository: QuestionRepository) {

    fun execute() : Flow<List<QuestionModel>> = questionRepository.getQuestionsFromDB()

}