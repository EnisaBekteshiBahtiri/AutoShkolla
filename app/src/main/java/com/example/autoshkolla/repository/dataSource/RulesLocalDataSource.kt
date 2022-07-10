package com.example.autoshkolla.repository.dataSource

import com.example.autoshkolla.model.RuleModel
import kotlinx.coroutines.flow.Flow

interface RulesLocalDataSource {
    fun getRulesFromDB() : Flow<List<RuleModel>>
    suspend fun insertRuleToDB(rule: RuleModel)
    suspend fun deleteRuleFromDB(id: Int)
}