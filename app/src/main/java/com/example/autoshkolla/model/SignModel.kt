package com.example.autoshkolla.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Keep
data class SignModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var url: String,
    var title: String,
    var description: String
)
