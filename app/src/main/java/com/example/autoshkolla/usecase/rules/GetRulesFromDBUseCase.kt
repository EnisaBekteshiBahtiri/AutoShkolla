package com.example.autoshkolla.usecase.rules

import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.repository.main.RuleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRulesFromDBUseCase  @Inject constructor(private val ruleRepository: RuleRepository) {

    fun execute() : Flow<List<RuleModel>> = ruleRepository.getRulesFromDB()

}