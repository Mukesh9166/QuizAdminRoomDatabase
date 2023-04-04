package com.ms.quizapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "quiz_questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var question: String,
    var questionImageUri: String?,
    var option1: String,
    var option2: String,
    var option3: String,
    var correctAnswer: Int
): Serializable