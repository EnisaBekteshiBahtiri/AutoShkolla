package com.example.autoshkolla.repository.main

import com.example.autoshkolla.model.SignModel
import kotlinx.coroutines.flow.Flow

interface SignRepository {
    fun getSignsFromDB() : Flow<List<SignModel>>
    suspend fun insertSignDataToDB(signModel: SignModel)
    suspend fun deleteSignDataFromDB(id: Int)
}