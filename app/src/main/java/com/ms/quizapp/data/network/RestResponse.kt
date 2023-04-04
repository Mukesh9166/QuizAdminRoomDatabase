package com.ms.quizapp.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestResponse<T>
    (
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("data")
    @Expose
    val data: T,

    @SerializedName("total")
    @Expose
    val total: Int?,
    @SerializedName("page")
    @Expose
    val page: Int?,
    @SerializedName("limit")
    @Expose
    val limit: Int?,
    @SerializedName("error")
    @Expose
    val error: String?

)