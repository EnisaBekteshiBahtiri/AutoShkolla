package com.example.autoshkolla.repository.main

import com.example.autoshkolla.model.RuleModel
import kotlinx.coroutines.flow.Flow

interface RuleRepository {
    fun getRulesFromDB() : Flow<List<RuleModel>>
    suspend fun insertRuleDataToDB(ruleModel: RuleModel)
    suspend fun deleteRuleDataFromDB(id: Int)
}