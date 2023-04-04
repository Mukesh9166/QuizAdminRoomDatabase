package com.ms.quizapp.ui.viewQuestions.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ms.quizapp.data.database.QuestionPagingSource
import com.ms.quizapp.data.database.QuizDatabase
import com.ms.quizapp.data.models.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ViewQuestionViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "ViewQuestionViewModel"
    private val questionDao = QuizDatabase.getDatabase(application).quizDao()


    private var questionPagingSource = QuestionPagingSource(questionDao)
    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)
    var questionsFlow = Pager(config = pagingConfig, pagingSourceFactory = { questionPagingSource }).flow

    val allQuestionsPaged: Flow<PagingData<Question>> get() = _allQuestionsPaged

    private val _allQuestionsPaged = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {questionPagingSource ?: QuestionPagingSource(questionDao).also {
            questionPagingSource = it
        } }
    ).flow.cachedIn(viewModelScope)


    fun deleteQuestion(question: Question) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                questionDao.deleteQuestion(question = question)

                questionPagingSource.invalidate()
                questionPagingSource = QuestionPagingSource(questionDao)
            }catch (e:Exception){
                e.stackTrace
            }
        }

    }

}