package com.ms.quizapp.data.repositories

import android.content.Context
import com.ms.quizapp.R
import com.ms.quizapp.data.network.RetrofitClient
import com.ms.quizapp.data.network.SafeApiRequest

class QuizApiRepository : SafeApiRequest() {


    suspend fun getUserList(context: Context, page: Int = 0, limit: Int = 10) =
        apirequest {
            RetrofitClient.restApi(context).getUserList(
                context.resources.getString(R.string.app_id),
                page, limit
            )
        }
}