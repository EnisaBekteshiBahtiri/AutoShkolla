package com.example.autoshkolla.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Keep
data class QuestionModel(
    @PrimaryKey
val id: Int,
val question: String,
val optionOne: String,
val optionTwo: String,
val optionThree: String,
val optionFour: String,
val correctAnswer: Int
)

