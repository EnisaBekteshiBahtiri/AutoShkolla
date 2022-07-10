package com.example.autoshkolla.repository.main

import com.example.autoshkolla.model.SignModel
import com.example.autoshkolla.repository.dataSource.SignsLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val localDataSource: SignsLocalDataSource
) : SignRepository {
    override fun getSignsFromDB(): Flow<List<SignModel>> {
        return localDataSource.getSignsFromDB()
    }
    override suspend fun insertSignDataToDB(signModel: SignModel) {
        return localDataSource.insertSignToDB(signModel)
    }

    override suspend fun deleteSignDataFromDB(id: Int) {
        return localDataSource.deleteSignFromDB(id)
    }
}