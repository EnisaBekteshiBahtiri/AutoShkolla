package com.example.autoshkolla.repository.dataSource

import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.model.SignModel
import kotlinx.coroutines.flow.Flow

interface SignsLocalDataSource {
    fun getSignsFromDB() : Flow<List<SignModel>>
    suspend fun insertSignToDB(sign: SignModel)
    suspend fun deleteSignFromDB(id: Int)
}