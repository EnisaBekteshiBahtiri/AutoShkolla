package com.example.autoshkolla.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.autoshkolla.model.RuleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RuleDao {
    @Query("SELECT * FROM RuleModel")
    fun getRulesFromDB(): Flow<List<RuleModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRulesToDB(rule: RuleModel)

    @Query("DELETE FROM RuleModel where id=:id")
    suspend fun deleteRulesFromDB(id: Int)
}