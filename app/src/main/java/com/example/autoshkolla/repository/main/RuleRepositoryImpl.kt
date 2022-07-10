package com.example.autoshkolla.repository.main

import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.repository.dataSource.RulesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RuleRepositoryImpl  @Inject constructor(
    private val localDataSource: RulesLocalDataSource
) : RuleRepository {

    override fun getRulesFromDB(): Flow<List<RuleModel>> {
        return localDataSource.getRulesFromDB()
    }

    override suspend fun insertRuleDataToDB(ruleModel: RuleModel) {
        return localDataSource.insertRuleToDB(ruleModel)
    }

    override suspend fun deleteRuleDataFromDB(id: Int) {
       return localDataSource.deleteRuleFromDB(id)
    }

}