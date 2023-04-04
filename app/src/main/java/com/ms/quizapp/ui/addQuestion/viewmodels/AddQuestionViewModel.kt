package com.ms.quizapp.ui.addQuestion.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ms.quizapp.data.database.QuizDatabase
import com.ms.quizapp.data.models.Question
import com.ms.quizapp.data.network.dataloadstatus.DataLoadStatus
import com.ms.quizapp.data.network.models.UserDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddQuestionViewModel(application: Application) : AndroidViewModel(application)  {

    private val questionDao = QuizDatabase.getDatabase(application).quizDao()

    private val addQuestionStatusLiveData: MutableLiveData<Boolean> =
        MutableLiveData()

    val addQuestionLoadStatus: LiveData<Boolean>
        get() = addQuestionStatusLiveData

    private val updateQuestionStatusLiveData: MutableLiveData<Boolean> =
        MutableLiveData()

    val updateQuestionLoadStatus: LiveData<Boolean>
        get() = updateQuestionStatusLiveData

    fun addQuestion(question: Question) {
        addQuestionStatusLiveData.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            questionDao.addQuestion(question)
            addQuestionStatusLiveData.postValue(true)
        }
    }

    fun updateQuestion(question: Question) {
        updateQuestionStatusLiveData.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            questionDao.updateQuestion(question)
            updateQuestionStatusLiveData.postValue(true)
        }
    }


}