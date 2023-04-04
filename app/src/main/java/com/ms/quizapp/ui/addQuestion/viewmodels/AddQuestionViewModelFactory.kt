package com.ms.quizapp.ui.addQuestion.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddQuestionViewModelFactory (val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddQuestionViewModel(application) as T
    }
}