package com.example.autoshkolla.repository.dataSourceImpl

import com.example.autoshkolla.database.dao.SignDao
import com.example.autoshkolla.model.SignModel
import com.example.autoshkolla.repository.dataSource.SignsLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignsLocalDataSourceImpl @Inject constructor(
    private val getSignsDao: SignDao
) : SignsLocalDataSource {
    override fun getSignsFromDB(): Flow<List<SignModel>> {
        return getSignsDao.getSignsFromDB()
    }

    override suspend fun insertSignToDB(sign: SignModel) {
        return getSignsDao.insertSignToDB(sign)
    }

    override suspend fun deleteSignFromDB(id: Int) {
       return getSignsDao.deleteSignFromDB(id)
    }
}