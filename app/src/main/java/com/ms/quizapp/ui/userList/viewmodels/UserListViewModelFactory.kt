package com.ms.quizapp.ui.userList.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListViewModelFactory(val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(application) as T
    }
}