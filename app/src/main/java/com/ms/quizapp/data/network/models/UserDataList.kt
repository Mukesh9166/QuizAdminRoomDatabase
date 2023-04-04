package com.ms.quizapp.data.network.models
import com.google.gson.annotations.SerializedName


data class UserDataList(
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("picture")
    val picture: String?,
    @SerializedName("title")
    val title: String?
)