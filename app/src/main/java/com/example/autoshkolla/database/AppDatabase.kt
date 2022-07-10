package com.example.autoshkolla.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.autoshkolla.database.dao.QuestionDao
import com.example.autoshkolla.database.dao.RuleDao
import com.example.autoshkolla.database.dao.SignDao
import com.example.autoshkolla.model.QuestionModel
import com.example.autoshkolla.model.RuleModel
import com.example.autoshkolla.model.SignModel

@Database(
    entities = [RuleModel::class,QuestionModel::class,SignModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract  class AppDatabase : RoomDatabase(){
    abstract fun getRulesDao(): RuleDao
    abstract fun getSignDao(): SignDao
    abstract fun getQuestionDao(): QuestionDao

}