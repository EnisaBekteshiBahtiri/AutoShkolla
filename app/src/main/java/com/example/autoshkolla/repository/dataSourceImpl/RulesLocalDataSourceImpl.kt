package com.example.autoshkolla.repository.dataSourceImpl

import com.example.autoshkolla.database.dao.RuleDao
import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.repository.dataSource.RulesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RulesLocalDataSourceImpl @Inject constructor(
    private val getRulesDao: RuleDao
) : RulesLocalDataSource {
    override fun getRulesFromDB(): Flow<List<RuleModel>> {
        return getRulesDao.getRulesFromDB()
    }

    override suspend fun insertRuleToDB(rule: RuleModel) {
       return getRulesDao.insertRulesToDB(rule)
    }

    override suspend fun deleteRuleFromDB(id: Int) {
        return getRulesDao.deleteRulesFromDB(id)
    }
}