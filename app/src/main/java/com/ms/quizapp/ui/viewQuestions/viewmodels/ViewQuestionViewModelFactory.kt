package com.ms.quizapp.ui.viewQuestions.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewQuestionViewModelFactory(val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewQuestionViewModel(application) as T
    }
}