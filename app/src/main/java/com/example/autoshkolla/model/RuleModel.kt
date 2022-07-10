package com.example.autoshkolla.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Keep
data class RuleModel(
    @PrimaryKey
    var id: Int,
    var title: String,
    var description: String
)