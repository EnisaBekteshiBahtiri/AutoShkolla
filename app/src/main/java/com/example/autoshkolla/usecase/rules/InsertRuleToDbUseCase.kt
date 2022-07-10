package com.example.autoshkolla.usecase.rules

import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.repository.main.RuleRepository
import javax.inject.Inject

class InsertRuleToDbUseCase  @Inject constructor(private val ruleRepository: RuleRepository) {
    suspend fun execute(ruleModel: RuleModel): Unit =
        ruleRepository.insertRuleDataToDB(ruleModel)
}