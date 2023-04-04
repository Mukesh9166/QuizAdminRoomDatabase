package com.ms.quizapp.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.ms.quizapp.data.models.Question

@Dao
interface QuizDao {

    @Insert
    suspend fun addQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("SELECT * FROM quiz_questions ORDER BY id DESC LIMIT :pageSize OFFSET :offset")
    suspend fun getQuestions(offset: Int, pageSize: Int): MutableList<Question>

    @Query("SELECT * FROM quiz_questions ORDER BY id DESC")
    fun getAllQuestionsPaged(): PagingSource<Int, Question>

    @Query("SELECT * FROM quiz_questions ORDER BY id DESC")
    fun getAllQuestions(): MutableList<Question>

}