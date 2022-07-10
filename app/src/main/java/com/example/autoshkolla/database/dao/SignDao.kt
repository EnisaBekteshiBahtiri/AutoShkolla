package com.example.autoshkolla.database.dao

import androidx.room.*
import com.example.autoshkolla.model.SignModel
import kotlinx.coroutines.flow.Flow


@Dao
interface SignDao {

    @Query("SELECT * FROM SignModel")
    fun getSignsFromDB(): Flow<List<SignModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSignToDB(signModel: SignModel)

    @Query("DELETE FROM SignModel where id=:id")
    suspend fun deleteSignFromDB(id: Int)
}

