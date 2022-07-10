package com.example.autoshkolla.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.autoshkolla.model.QuestionModel
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM QuestionModel")
    fun getQuestionsFromDB(): Flow<List<QuestionModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionToDB(question: QuestionModel)

    @Query("DELETE FROM QuestionModel where id=:id")
    suspend fun deleteQuestionFromDB(id: Int)
}