package com.ms.quizapp.data.network



import com.ms.quizapp.data.network.models.UserDataList
import retrofit2.Response
import retrofit2.http.*


interface RestApi {

    @GET(Apis.USER_LIST)
    suspend fun getUserList(
        @Header("app-id") appId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<RestResponse<MutableList<UserDataList>>>


}
