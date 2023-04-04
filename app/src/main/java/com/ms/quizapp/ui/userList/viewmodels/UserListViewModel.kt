package com.ms.quizapp.ui.userList.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ms.quizapp.R


import com.ms.quizapp.data.network.dataloadstatus.DataLoadStatus
import com.ms.quizapp.data.network.models.UserDataList
import com.ms.quizapp.data.repositories.QuizApiRepository
import com.ms.quizapp.utils.isNetworkConnected
import com.ms.quizapp.utils.showToast
import kotlinx.coroutines.launch

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = UserListViewModel::class.java.simpleName

    private val userListStatusLiveData: MutableLiveData<DataLoadStatus<MutableList<UserDataList>>> =
        MutableLiveData(DataLoadStatus.idle())

    val userListLoadStatus: LiveData<DataLoadStatus<MutableList<UserDataList>>>
        get() = userListStatusLiveData

    var hasMore = false
    var currentPage = 0





    fun getUserList(limit:Int = 10,page:Int = 0) {
        currentPage = page
        viewModelScope.launch {
            try {
                userListStatusLiveData.postValue(DataLoadStatus.started())

                val userDataResponse =
                    QuizApiRepository().getUserList(
                        getApplication(),
                        page=page,limit=limit,
                    )

                if (userDataResponse != null) {
                    userListStatusLiveData.postValue(DataLoadStatus.success(userDataResponse.data))
                } else {
                    userListStatusLiveData.postValue(
                        DataLoadStatus.fail(
                            userDataResponse.error ?: ""
                        )
                    )
                }
            } catch (e: Exception) {
                userListStatusLiveData.postValue(DataLoadStatus.fail("" + e.localizedMessage))
            }
        }

    }

}